package project.api.app.Selection

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.api.app.Selection.data.CurrentStage
import project.api.app.Selection.data.SelectionKanbanCardDTO
import project.api.app.Selection.data.SelectionProcess
import project.api.core.CrudController

@RestController
@RequestMapping("/selection-process")
class SelectionProcessController(
    val spService: SelectionProcessService
) : CrudController<SelectionProcess>(spService) {

    /** cards de uma coluna (fase) */
    @GetMapping("/kanban/{stage}")
    fun listByStage(@PathVariable stage: CurrentStage): ResponseEntity<List<SelectionKanbanCardDTO>> {
        val list = spService.listByStage(stage)
        return ResponseEntity.ok(list)
    }

    @GetMapping("/kanban")
    fun listAll(): ResponseEntity<List<SelectionProcess>> {
        val list = spService.listAll()
        return ResponseEntity.ok(list)
    }

    /** mover card (drag & drop) para outra coluna */
    @PatchMapping("/{id}/stage/{stage}")
    fun moveToStage(
        @PathVariable id: Int,
        @PathVariable stage: CurrentStage
    ): ResponseEntity<SelectionProcess> {
        val updated = spService.moveToStage(id, stage)
        return ResponseEntity.ok(updated)
    }

    /** busca do input "Busque por cards, assuntos ou responsáveis..." */
    @GetMapping("/kanban/search")
    fun search(@RequestParam q: String): ResponseEntity<List<SelectionKanbanCardDTO>> {
        val list = spService.searchCards(q)
        return ResponseEntity.ok(list)
    }
}
