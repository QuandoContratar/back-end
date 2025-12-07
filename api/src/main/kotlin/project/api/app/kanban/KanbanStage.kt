package project.api.app.kanban

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "kanban_stage")
data class KanbanStage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_stage: Int = 0,

    val name: String,

    val position_order: Int
)
