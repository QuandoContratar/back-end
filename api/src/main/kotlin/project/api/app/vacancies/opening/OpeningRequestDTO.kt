package project.api.app.vacancies.opening

data class OpeningRequestDTO(
    val cargo: String,
    val periodo: String,
    val modeloTrabalho: String,
    val regimeContratacao: String,
    val salario: Double,
    val localidade: String,
    val requisitos: String? = null,
    val gestorId: Int  // Apenas o ID do gestor, não o objeto completo
)

