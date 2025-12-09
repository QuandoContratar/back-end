package project.api.app.match

import org.springframework.stereotype.Service
import project.api.app.candidates.CandidateProfileRepository
import project.api.app.candidates.data.Candidate
import project.api.app.candidates.data.CandidateProfile
import project.api.app.candidates.data.SeniorityLevel
import project.api.app.match.parser.RequirementsParser
import project.api.app.match.parser.VacancyRequirementsParsed
import project.api.app.vacancies.data.Vacancy

@Service
class CandidateSmartMatchService(
    private val profileRepository: CandidateProfileRepository
) {

    fun calculateMatch(candidate: Candidate, vacancy: Vacancy): Double {

        val profile = profileRepository.findByCandidate(candidate)
            ?: return 0.0 // Se não tem perfil, não dá pra avaliar Match V3

        val parsed = RequirementsParser.parse(vacancy.requirements ?: "")

        val scoreSkills = scoreHardSkills(profile, parsed)
        val scoreSoftSkills = scoreSoftSkills(profile, parsed)
        val scoreExperience = scoreExperience(profile, parsed)
        val scoreSeniority = scoreSeniority(profile, parsed)
        val scoreStack = scoreStack(profile, parsed)
        val scoreLocation = scoreLocation(profile, parsed)
        val scoreFormat = scoreWorkFormat(profile, parsed)

        // PESOS — NIVEL 3
        val finalScore =
            (scoreSkills * 0.30) +
                    (scoreSoftSkills * 0.10) +
                    (scoreExperience * 0.25) +
                    (scoreSeniority * 0.10) +
                    (scoreStack * 0.10) +
                    (scoreLocation * 0.10) +
                    (scoreFormat * 0.05)

        return finalScore.coerceIn(0.0, 100.0)
    }

    // --------------------- SKILLS DURAS ---------------------

    private fun scoreHardSkills(profile: CandidateProfile, req: VacancyRequirementsParsed): Double {
        val cand = (profile.hardSkills ?: "").lowercase().split(",")
        val required = req.requiredSkills.map { it.lowercase() }

        if (required.isEmpty()) return 100.0

        val matched = required.count { skill -> cand.any { it.contains(skill) } }

        return (matched.toDouble() / required.size) * 100
    }

    // --------------------- SKILLS COMPORTAMENTAIS ---------------------

    private fun scoreSoftSkills(profile: CandidateProfile, req: VacancyRequirementsParsed): Int {
        val cand = (profile.softSkills ?: "").lowercase()

        val softList = listOf(
            "comunicação", "liderança", "proatividade",
            "organização", "trabalho em equipe", "negociação"
        )

        val matched = softList.count { cand.contains(it) }

        return (matched * 20).coerceAtMost(100) // 5 soft skills = 100%
    }

    // --------------------- EXPERIÊNCIA ---------------------

    private fun scoreExperience(profile: CandidateProfile, req: VacancyRequirementsParsed): Double {

        val candYears = profile.totalExperienceYears ?: return 50.0

        val reqYears = req.minExperienceYears ?: return 100.0

        return when {
            candYears >= reqYears -> 100.0
            candYears >= reqYears - 1 -> 70.0
            candYears >= reqYears - 2 -> 40.0
            else -> 20.0
        }
    }

    // --------------------- SENIORIDADE ---------------------

    private fun scoreSeniority(profile: CandidateProfile, req: VacancyRequirementsParsed): Double {

        val vagaSeniority = extractSeniorityFromText(req)
        val candSeniority = profile.mainSeniority ?: return 50.0

        if (vagaSeniority == null) return 100.0

        return if (candSeniority == vagaSeniority) 100.0 else 40.0
    }

    private fun extractSeniorityFromText(req: VacancyRequirementsParsed): SeniorityLevel? {
        return when {
            req.requiredSkills.any { it.contains("junior") } -> SeniorityLevel.JUNIOR
            req.requiredSkills.any { it.contains("pleno") } -> SeniorityLevel.PLENO
            req.requiredSkills.any { it.contains("senior") } -> SeniorityLevel.SENIOR
            else -> null
        }
    }

    // --------------------- TECNOLOGIA PRINCIPAL ---------------------

    private fun scoreStack(profile: CandidateProfile, req: VacancyRequirementsParsed): Double {

        val mainStack = profile.mainStack ?: return 50.0

        return if (req.requiredSkills.any { mainStack.contains(it, ignoreCase = true) })
            100.0 else 40.0
    }

    // --------------------- LOCALIZAÇÃO ---------------------

    private fun scoreLocation(profile: CandidateProfile, req: VacancyRequirementsParsed): Double {

        val state = profile.state ?: return 40.0

        if (req.remoteAllowed) return 100.0

        return if (state.equals(req.requiredState, ignoreCase = true)) 100.0 else 40.0
    }

    // --------------------- FORMATO DE TRABALHO ---------------------

    private fun scoreWorkFormat(profile: CandidateProfile, req: VacancyRequirementsParsed): Double {

        val pref = profile.remotePreference?.lowercase() ?: return 60.0

        return when {
            req.remoteAllowed && pref == "remoto" -> 100.0
            !req.remoteAllowed && pref == "presencial" -> 100.0
            pref == "hibrido" -> 80.0
            else -> 40.0
        }
    }
}
