package project.api.app.Selection.data

import java.time.LocalDateTime

data class SelectionKanbanCardDTO(
    val processId: Int,
    val candidateId: Int?,
    val candidateName: String,
    val vacancyId: Int?,
    val vacancyTitle: String,
    val workModel: String?,
    val contractType: String?,
    val managerName: String?,
    val currentStage: CurrentStage,
    val progress: Double,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromEntity(sp: SelectionProcess) = SelectionKanbanCardDTO(
            processId = sp.idSelection!!,
            candidateId = sp.candidate?.idCandidate,
            candidateName = sp.candidate?.name ?: "",
            vacancyId = sp.vacancy?.id,
            vacancyTitle = sp.vacancy?.position_job ?: "",
            workModel = sp.vacancy?.workModel?.name,          // "presencial"/"remoto"/"híbrido"
            contractType = sp.vacancy?.contractType?.name,    // "CLT"/"PJ"/...
            managerName = sp.vacancy?.manager?.name,
            currentStage = sp.currentStage,
            progress = sp.progress,
            createdAt = sp.createdAt
        )
    }
}
