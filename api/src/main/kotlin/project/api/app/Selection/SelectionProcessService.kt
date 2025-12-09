package project.api.app.Selection

import org.springframework.stereotype.Service
import project.api.app.Selection.data.*
import project.api.core.CrudService

@Service
class SelectionProcessService(
    override val repository: SelectionProcessRepository
) : CrudService<SelectionProcess>(repository) {

    fun listByStage(stage: CurrentStage): List<SelectionKanbanCardDTO> =
        repository.findByCurrentStage(stage).map { SelectionKanbanCardDTO.fromEntity(it) }

    fun searchCards(query: String): List<SelectionKanbanCardDTO> =
        repository.search(query).map { SelectionKanbanCardDTO.fromEntity(it) }

    fun moveToStage(id: Int, newStage: CurrentStage): SelectionProcess {
        val sp = findById(id)
        sp.currentStage = newStage
        sp.progress = progressFor(newStage)
        return repository.save(sp)
    }

    /** regra simples: mapeia estágio -> % de progresso */
    private fun progressFor(stage: CurrentStage): Double = when (stage) {
        CurrentStage.aguardando_triagem     -> 0.0
        CurrentStage.triagem                -> 10.0
        CurrentStage.entrevista_rh          -> 18.0   // etapa intermediária
        CurrentStage.avaliacao_fit_cultural -> 30.0
        CurrentStage.teste_tecnico          -> 50.0
        CurrentStage.entrevista_tecnica     -> 70.0
        CurrentStage.entrevista_final       -> 85.0
        CurrentStage.proposta_fechamento    -> 95.0
        CurrentStage.contratacao            -> 100.0
    }


    fun listAll(): List<SelectionProcess> = repository.findAll()
}
