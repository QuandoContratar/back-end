package project.api.app.dashboard


data class VacancyCandidateDTO(
    val id: Int,
    val nome: String,
    val contrato: String?,
    val periodo: String?,
    val modelo: String?,
    val localidade: String?,
    val gestor: String?,
    val matching: Double
)
