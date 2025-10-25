package project.api.app.match


import org.springframework.stereotype.Service
import project.api.app.candidates.data.Candidate
import project.api.app.vacancies.data.Vacancy

@Service
class CandidateMatchService {

    // Exemplo de cálculo de match
    fun calculateMatch(candidate: Candidate, vacancy: Vacancy): Double {
        var score = 0.0

        if (!candidate.skills.isNullOrBlank() && !vacancy.requirements.isNullOrBlank()) {
            val candidateSkills = candidate.skills!!.lowercase().split(",")
            val vacancyReqs = vacancy.requirements!!.lowercase().split(",")

            val matches = candidateSkills.count { skill -> vacancyReqs.any { it.trim() == skill.trim() } }
            score = (matches.toDouble() / vacancyReqs.size) * 100
        }

        return score
    }

    fun getMatchLevel(score: Double): MatchLevel {
        return when {
            score >= 90 -> MatchLevel.DESTAQUE
            score >= 70 -> MatchLevel.ALTO
            score >= 50 -> MatchLevel.MEDIO
            else -> MatchLevel.BAIXO
        }
    }
}
