package project.api.app.kanban

data class KanbanCardDTO(
    val processId: Int,
    val candidateId: Int?,
    val candidateName: String,
    val vacancyId: Int?,
    val vacancyTitle: String,
    val workModel: String?,
    val contractType: String?,
    val managerName: String?,
    val currentStage: String,
    val rejectionReason: String?,
    val progress: Double
) {
    companion object {
        fun from(card: KanbanCard, totalStages: Int): KanbanCardDTO {
            val stageOrder = card.stage.position_order
            val progress = (stageOrder.toDouble() / totalStages.toDouble()) * 100.0

            return KanbanCardDTO(
                processId = card.id_card,
                candidateId = card.candidate.idCandidate,
                candidateName = card.candidate.name ?: "",
                vacancyId = card.vacancy.id,
                vacancyTitle = card.vacancy.position_job ?: "",
                workModel = card.vacancy.workModel?.name,
                contractType = card.vacancy.contractType?.name,
                managerName = card.vacancy.manager?.name,
                currentStage = card.stage.name,
                rejectionReason = card.rejectionReason,// ex: "triagem", "entrevista_rh"
                progress = progress
            )
        }
    }
}
