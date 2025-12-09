package project.api.app.match

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import project.api.app.candidates.CandidateProfileRepository
import project.api.app.candidates.data.Candidate
import project.api.app.match.parser.RequirementsParser
import project.api.app.match.parser.VacancyRequirementsParsed
import project.api.app.vacancies.data.Vacancy

@Service
class CandidateMatchService {
    @Autowired
    lateinit var candidateProfileRepository: CandidateProfileRepository


    // -----------------------------------------------------
    // Função principal: calcula o Smart Match final (0–100)
    // -----------------------------------------------------
    fun calculateMatch(candidate: Candidate, vacancy: Vacancy): Double {

        // O parser vai tentar extrair tudo somente do campo requirements
        val parsed: VacancyRequirementsParsed =
            RequirementsParser.parse(vacancy.requirements ?: "")

        val scoreSkills = scoreSkills(candidate, parsed)
        val scoreExp = scoreExperience(candidate, parsed)
        val scoreEdu = scoreEducation(candidate, parsed)
        val scoreLocation = scoreLocation(candidate, parsed)

        val finalScore =
            (scoreSkills * 0.40) +     // 40% habilidades
                    (scoreExp * 0.25) +        // 25% experiência
                    (scoreEdu * 0.15) +        // 15% escolaridade
                    (scoreLocation * 0.10) +   // 10% localização
                    0.10                       // bônus fixo

        return finalScore.coerceIn(0.0, 100.0)
    }

    // -----------------------------------------------------
    // CLASSIFICAÇÃO: transforma score → MatchLevel
    // -----------------------------------------------------
    fun getMatchLevel(score: Double): MatchLevel {
        return when {
            score >= 90 -> MatchLevel.DESTAQUE
            score >= 70 -> MatchLevel.ALTO
            score >= 50 -> MatchLevel.MEDIO
            else -> MatchLevel.BAIXO
        }
    }

    // -----------------------------------------------------
    // -------------- FUNÇÕES PRIVADAS DE SCORING ----------
    // -----------------------------------------------------

    private fun scoreSkills(candidate: Candidate, req: VacancyRequirementsParsed): Double {
        val candSkills = (candidate.skills ?: "").lowercase()

        val matched = req.requiredSkills.count { candSkills.contains(it) }
        val totalRequired = if (req.requiredSkills.isNotEmpty()) req.requiredSkills.size else 1

        return (matched.toDouble() / totalRequired) * 100
    }

    private fun scoreExperience(candidate: Candidate, req: VacancyRequirementsParsed): Double {
        if (req.minExperienceYears == null) return 100.0

        val years = extractYearsFromCandidate(candidate.experience ?: "")

        return when {
            years >= req.minExperienceYears!! -> 100.0
            years == req.minExperienceYears!! - 1 -> 60.0
            else -> 20.0
        }
    }

    private fun extractYearsFromCandidate(exp: String): Int {
        val regex = Regex("(\\d+)\\s*(anos|ano)")
        return regex.find(exp.lowercase())
            ?.groups?.get(1)?.value?.toIntOrNull() ?: 0
    }

    private fun scoreEducation(candidate: Candidate, req: VacancyRequirementsParsed): Double {
        val candEdu = (candidate.education ?: "").lowercase()

        return when {
            req.requiredEducationLevel == null -> 100.0
            candEdu.contains(req.requiredEducationLevel!!.lowercase()) -> 100.0
            else -> 40.0
        }
    }

    private fun scoreLocation(candidate: Candidate, req: VacancyRequirementsParsed): Double {
        if (req.requiredState == null) return 100.0
        return if (candidate.state?.lowercase() == req.requiredState.lowercase()) 100.0 else 40.0
    }
}

// Exemplo de cálculo de match
//    fun calculateMatch(candidate: Candidate, vacancy: Vacancy): Double {
//        var score = 0.0
//
//        if (!candidate.skills.isNullOrBlank() && !vacancy.requirements.isNullOrBlank()) {
//            val candidateSkills = candidate.skills!!.lowercase().split(",")
//            val vacancyReqs = vacancy.requirements!!.lowercase().split(",")
//
//            val matches = candidateSkills.count { skill -> vacancyReqs.any { it.trim() == skill.trim() } }
//            score = (matches.toDouble() / vacancyReqs.size) * 100
//        }
//
//        return score
//    }
//
//    fun getMatchLevel(score: Double): MatchLevel {
//        return when {
//            score >= 90 -> MatchLevel.DESTAQUE
//            score >= 70 -> MatchLevel.ALTO
//            score >= 50 -> MatchLevel.MEDIO
//            else -> MatchLevel.BAIXO
//        }
//    }
//}