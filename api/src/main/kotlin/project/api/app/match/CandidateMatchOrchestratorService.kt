package project.api.app.match

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import project.api.app.candidates.CandidateProfileRepository
import project.api.app.candidates.CandidateRepository
import project.api.app.vacancies.VacancyRepository

@Service
class CandidateMatchOrchestratorService(
    private val candidateRepository: CandidateRepository,
    private val vacancyRepository: VacancyRepository,
    private val matchRepository: CandidateMatchRepository,
    private val smartMatchService: CandidateSmartMatchService,
    private val profileRepository: CandidateProfileRepository
) {
@Transactional
    fun generateMatchForSingleVacancy(candidateId: Int, vacancyId: Long) {

        val candidate = candidateRepository.findById(candidateId)
            .orElseThrow { RuntimeException("Candidato não encontrado") }

        val vacancy = vacancyRepository.findById(vacancyId.toInt())
            .orElseThrow { RuntimeException("Vaga não encontrada") }

    val profile = profileRepository.findByCandidate(candidate)?: throw RuntimeException("CandidateProfile não encontrado")

        val score = smartMatchService.calculateMatch(candidate, vacancy)

        val level = when {
            score >= 90 -> MatchLevel.DESTAQUE
            score >= 70 -> MatchLevel.ALTO
            score >= 50 -> MatchLevel.MEDIO
            else -> MatchLevel.BAIXO
        }

        val existing = matchRepository.findByVacancyId(vacancyId.toInt())
            .firstOrNull { it.candidate.idCandidate == candidateId }

        if (existing != null) {
            existing.score = score
            existing.matchLevel = level
            existing.status = MatchStatus.PENDING
            matchRepository.save(existing)
            return
        }

        matchRepository.save(
            CandidateMatch(
                candidate = candidate,
                vacancy = vacancy,
                score = score,
                matchLevel = level,
                status = MatchStatus.PENDING
            )
        )
    }
}
