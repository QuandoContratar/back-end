package project.api.app.match

import project.api.app.candidates.data.Candidate
import project.api.app.vacancies.data.Vacancy


data class CandidateMatchDTO(
    val candidateId: Int,
    val candidateName: String,
    val vacancyId: Int,
    val vacancyJob: String,
    val score: Double,
    val matchLevel: MatchLevel
) {
    companion object {
        fun from(candidate: Candidate, vacancy: Vacancy, score: Double, matchLevel: MatchLevel): CandidateMatchDTO {
            return CandidateMatchDTO(
                candidateId = candidate.idCandidate ?: 0,
                candidateName = candidate.name ?: "",
                vacancyId = vacancy.id ?: 0,
                vacancyJob = vacancy.position_job ?: "",
                score = score,
                matchLevel = matchLevel
            )
        }
    }
}
