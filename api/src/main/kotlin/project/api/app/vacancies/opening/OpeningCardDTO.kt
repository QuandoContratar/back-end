package project.api.app.vacancies.opening


data class OpeningCardDTO(
    val id: Int?,
    val cargo: String,
    val salario: Double,
    val modeloTrabalho: String,
    val regimeContratacao: String,
    val gestorNome: String?
) {
    companion object {
        fun fromEntity(entity: OpeningRequest) = OpeningCardDTO(
            id = entity.id,
            cargo = entity.cargo,
            salario = entity.salario,
            modeloTrabalho = entity.modeloTrabalho,
            regimeContratacao = entity.regimeContratacao,
            gestorNome = entity.gestor.name
        )
    }
}
