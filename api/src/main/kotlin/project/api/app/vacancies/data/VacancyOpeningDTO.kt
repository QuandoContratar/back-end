package project.api.app.vacancies.data

data class VacancyOpeningDTO(
    val position_job: String?,
    val period: String?,
    val workModel: WorkModel?,
    val requirements: String?,
    val contractType: ContractType?,
    val salary: Double?,
    val location: String?,
    val area: String?,
    val statusVacancy: String?
)
