package project.api.app.vacancies.data

data class VacancyDto(
    val id: Int,
    val position: String,
    val period: String,
    val workModel: String,
    val requirements: String,
    val contractType: String,
    val salary: Double,
    val location: String,
    val openingJustification: String,
    val managerName: String
)