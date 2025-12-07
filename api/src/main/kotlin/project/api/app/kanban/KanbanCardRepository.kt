package project.api.app.kanban

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface KanbanCardRepository : JpaRepository<KanbanCard, Int> {

    @Query("""
        SELECT c FROM KanbanCard c
        JOIN FETCH c.candidate
        JOIN FETCH c.vacancy v
        JOIN FETCH v.manager
        JOIN FETCH c.stage
    """)
    fun findAllComplete(): List<KanbanCard>

    fun findByStage_Name(name: String): List<KanbanCard>

//    @Query("""
//   SELECT COUNT(*)
//   FROM kanban_card
//   WHERE fk_stage = (SELECT id_stage FROM kanban_stage WHERE name = 'contratacao')
//""", nativeQuery = true)
//    fun countContratados(): Long



}
