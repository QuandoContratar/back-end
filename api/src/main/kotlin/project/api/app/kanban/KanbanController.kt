package project.api.app.kanban

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import project.api.app.vacancies.opening.RejectCardRequest

@RestController
@RequestMapping("/selection-process")
class SelectionProcessKanbanProxyController(
    private val KanbanService: KanbanService
) {

    @GetMapping("/kanban")
    fun getAll(): List<KanbanCardDTO> = KanbanService.listAllCards()

    @GetMapping("/kanban/{stage}")
    fun getByStage(@PathVariable stage: String): List<KanbanCardDTO> =
        KanbanService.listByStage(stage)

    @PatchMapping("/{id}/stage/{stage}")
    fun moveToStage(
        @PathVariable id: Int,
        @PathVariable stage: String
    ): KanbanCardDTO = KanbanService.moveCardByStageName(id, stage)

    @GetMapping("/kanban/search")
    fun search(@RequestParam q: String): List<KanbanCardDTO> {
        // por enquanto, implementa um filtro básico em memória
        val all = KanbanService.listAllCards()
        val query = q.lowercase()
        return all.filter {
            it.candidateName.lowercase().contains(query)
            it.vacancyTitle.lowercase().contains(query)
            (it.managerName ?: "").lowercase().contains(query)
        }
    }

    @PatchMapping("/kanban/{id}/reject")
    fun rejectCard(
        @PathVariable id: Int,
        @RequestBody body: RejectCardRequest
    ): KanbanCardDTO = KanbanService.rejectCard(id, body.reason)

}