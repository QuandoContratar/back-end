package project.api.app.Selection

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import project.api.app.Selection.data.CurrentStage
import project.api.app.Selection.data.SelectionProcess
import project.api.app.candidates.data.Candidate

interface SelectionProcessRepository: JpaRepository<SelectionProcess, Int> {

//    fun findByCandidate(candidate: Candidate): List<SelectionProcess>
fun findTopByCandidateAndProgressLessThanOrderByCreatedAtDesc(candidate: Candidate, progress: Double): SelectionProcess?

//    fun findByCandidateOrderByCreatedAtDesc(candidate: Candidate): SelectionProcess?

    fun findByCurrentStage(stage: CurrentStage): List<SelectionProcess>

    @Query("""
        SELECT sp FROM SelectionProcess sp
        LEFT JOIN sp.candidate c
        LEFT JOIN sp.vacancy v
        LEFT JOIN v.manager m
        WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(v.position_job) LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(m.name) LIKE LOWER(CONCAT('%', :q, '%'))
    """)
    fun search(@Param("q") query: String): List<SelectionProcess>
}