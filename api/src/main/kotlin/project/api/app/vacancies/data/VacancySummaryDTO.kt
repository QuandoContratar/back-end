package project.api.app.vacancies.data

import project.api.app.users.data.User

data class VacancySummaryDTO(
    val id: Int,
    val positionJob: String,
    val workModel: String,
    val managerName: String,
    val area: String
)

