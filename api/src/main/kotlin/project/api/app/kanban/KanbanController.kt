package project.api.app.kanban

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/selection-process")
class SelectionProcessKanbanProxyController(
    private val kanbanService: KanbanService
) {
    // Todos os métodos foram removidos devido a conflitos de mapeamento com SelectionProcessController
    // Os endpoints estão disponíveis em:
    // - GET /selection-process/kanban - SelectionProcessController#listAll()
    // - GET /selection-process/kanban/{stage} - SelectionProcessController#listByStage(CurrentStage)
    // - PATCH /selection-process/{id}/stage/{stage} - SelectionProcessController#moveToStage(id, CurrentStage)
    // - GET /selection-process/kanban/search - SelectionProcessController#search(String)
}

