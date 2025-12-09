package project.api.app.dashboard

data class VacancyRecommendationDTO(
    val vacancyId: Int,
    val position: String,
    val score: Double,
    val matchLevel: String
)
