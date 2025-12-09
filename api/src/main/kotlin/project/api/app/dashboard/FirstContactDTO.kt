package project.api.app.dashboard

data class FirstContactDTO(
    val candidateId: Int,
    val candidateName: String,
    val daysUntilContact: Int
)
