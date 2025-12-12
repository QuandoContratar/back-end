package project.api.app.kanban

import org.springframework.stereotype.Service

@Service
class KanbanService(
    private val cardRepository: KanbanCardRepository,
    private val stageRepository: KanbanStageRepository
) {

    fun listAllCards(): List<KanbanCardDTO> {
        val cards = cardRepository.findAllComplete()
        val totalStages = stageRepository.count().toInt().coerceAtLeast(1)

        return cards.map { KanbanCardDTO.from(it, totalStages) }
    }

    fun listByStage(stageName: String): List<KanbanCardDTO> {
        val cards = cardRepository.findByStage_Name(stageName)
        val totalStages = stageRepository.count().toInt().coerceAtLeast(1)

        return cards.map { KanbanCardDTO.from(it, totalStages) }
    }

    fun moveCardByStageName(cardId: Int, newStageName: String): KanbanCardDTO {
        val card = cardRepository.findById(cardId).orElseThrow()
        val newStage = stageRepository.findByName(newStageName)
            ?: throw RuntimeException("Stage '$newStageName' não encontrado")

        val updated = card.copy(stage = newStage)
        val saved = cardRepository.save(updated)
        val totalStages = stageRepository.count().toInt().coerceAtLeast(1)

        return KanbanCardDTO.from(saved, totalStages)
    }

    fun rejectCard(cardId: Int, reason: String): KanbanCardDTO {
        val card = cardRepository.findById(cardId).orElseThrow()

        val rejectStage = stageRepository.findByName("rejeitados")
            ?: throw RuntimeException("Stage 'rejeitado' não encontrado")

        val updated = card.copy(
            stage = rejectStage,
            rejectionReason = reason
        )

        val saved = cardRepository.save(updated)

        val totalStages = stageRepository.count().toInt().coerceAtLeast(1)

        return KanbanCardDTO.from(saved, totalStages)
    }

}

