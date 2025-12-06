package project.api.app.kanban

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KanbanStageRepository : JpaRepository<KanbanStage, Int> {

    fun findByName(name: String): KanbanStage?
}
