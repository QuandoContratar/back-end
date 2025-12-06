package project.api.app.dashboard

data class TopCandidateMatchDTO(
    val matchId: Int,
    val candidateId: Int,
    val candidateName: String,
    val vacancyId: Int,
    val vacancyPosition: String,
    val score: Double,
    val matchLevel: String
)