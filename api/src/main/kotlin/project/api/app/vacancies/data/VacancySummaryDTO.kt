package project.api.app.vacancies.data

import project.api.app.users.data.User

data class VacancySummaryDTO(
    val positionJob: String,
    val workModel: WorkModel,
    val managerName: String,
    val area: String
)

