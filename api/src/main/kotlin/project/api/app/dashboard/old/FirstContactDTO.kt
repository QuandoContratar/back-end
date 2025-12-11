package project.api.app.dashboard.old

data class FirstContactDTO(
    val candidateId: Int,
    val candidateName: String,
    val daysUntilContact: Int
)