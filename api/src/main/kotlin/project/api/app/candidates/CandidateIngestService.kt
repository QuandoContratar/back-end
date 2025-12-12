package project.api.app.candidates


import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import project.api.app.candidates.CandidateProfileRepository
import project.api.app.candidates.CandidateRepository
import project.api.app.candidates.data.Candidate
import project.api.app.candidates.data.CandidateProfile
import project.api.app.candidates.data.SeniorityLevel
import project.api.app.candidates.dto.CandidateProfileDTO
import project.api.app.candidates.dto.ExperienceDTO
import project.api.app.match.CandidateMatch
import project.api.app.match.CandidateMatchRepository
import project.api.app.match.CandidateSmartMatchService
import project.api.app.match.MatchLevel
import project.api.app.match.MatchStatus
import project.api.app.vacancies.VacancyRepository


@Service
class CandidateIngestService(
    private val candidateRepository: CandidateRepository,
    private val profileRepository: CandidateProfileRepository,
    private val vacancyRepository: VacancyRepository,
    private val candidateMatchRepository: CandidateMatchRepository,
    private val smartMatchService: CandidateSmartMatchService
) {

    private fun normalizeSeniority(raw: String?): SeniorityLevel? {
        if (raw.isNullOrBlank()) return null

        // Remover acentos e padronizar
        val cleaned = java.text.Normalizer.normalize(raw, java.text.Normalizer.Form.NFD)
            .replace("[^\\p{ASCII}]".toRegex(), "")
            .trim()
            .uppercase()

        return when {
            // JUNIOR
            cleaned in listOf("JUNIOR", "JR", "JNR", "JUN", "JUNIOR.", "JUNIORA", "JUN") ->
                SeniorityLevel.JUNIOR

            // PLENO
            cleaned in listOf("PLENO", "PLENA", "PLN", "PL") ->
                SeniorityLevel.PLENO

            // SENIOR
            cleaned in listOf("SENIOR", "SR", "SNR", "SEN", "SENIOR.", "SENH") ->
                SeniorityLevel.SENIOR

            // LEAD
            cleaned in listOf("LEAD", "LIDER", "LIDER TECNICO", "TECH LEAD", "TL") ->
                SeniorityLevel.LEAD

            else -> null
        }
    }


    @Transactional
    fun ingest(dto: CandidateProfileDTO): Candidate {

        // 1. Criar o candidate
        val savedCandidate = candidateRepository.save(
            Candidate(
                name = dto.name,
                email = dto.email,
                //phone = dto.phone,
                state = dto.location?.state,
                education = dto.education?.joinToString("\n") { edu ->
                    "${edu.level} em ${edu.course} - ${edu.institution} (${edu.startYear}-${edu.endYear})"
                },
                skills = dto.skills?.joinToString(","),
                experience = dto.experiences?.joinToString("\n") { exp ->
                    "${exp.role} - ${exp.company} (${exp.start} à ${exp.end})"
                },
                pathResume = dto.pathResume,
                institution = dto.education[0].institution
            )
        )

        // 2. Criar o candidate_profile
        val rawJsonMap = mapOf(
            "education" to dto.education,
            "experiences" to dto.experiences,
            "skills" to dto.skills,
            "softSkills" to dto.softSkills,
            "seniority" to dto.seniority,
            "location" to dto.location
        )

        val mapper = com.fasterxml.jackson.module.kotlin.jacksonObjectMapper()
        val rawJsonString = mapper.writeValueAsString(rawJsonMap)

        profileRepository.save(
            CandidateProfile(
                candidate = savedCandidate,
                rawJson = rawJsonString,
                totalExperienceYears = dto.totalExperienceYears,
                mainSeniority = normalizeSeniority(dto.seniority),
                mainStack = dto.skills?.firstOrNull(),
                mainRole = dto.experiences.firstOrNull()?.role,
                city = dto.location?.city,
                state = dto.location?.state,
                remotePreference = dto.location?.workFormat ?: "REMOTO",
                hardSkills = dto.skills?.joinToString(","),
                softSkills = dto.softSkills?.joinToString(",")
            )
        )
        // 3. Gerar MATCH automático desse candidato com TODAS as vagas
        generateMatchesForCandidate(savedCandidate)

        return savedCandidate
    }

    private fun generateMatchesForCandidate(candidate: Candidate) {

        val vacancies = vacancyRepository.findAll()

        if (vacancies.isEmpty()) return

        val matches = vacancies.map { vacancy ->

            val score = smartMatchService.calculateMatch(candidate, vacancy)
            val level = getMatchLevel(score)

            CandidateMatch(
                candidate = candidate,
                vacancy = vacancy,
                score = score,
                matchLevel = level,
                status = MatchStatus.PENDING
            )
        }

        candidateMatchRepository.saveAll(matches)
    }


    private fun getMatchLevel(score: Double): MatchLevel =
        when {
            score >= 90 -> MatchLevel.DESTAQUE
            score >= 70 -> MatchLevel.ALTO
            score >= 50 -> MatchLevel.MEDIO
            else -> MatchLevel.BAIXO
        }
}