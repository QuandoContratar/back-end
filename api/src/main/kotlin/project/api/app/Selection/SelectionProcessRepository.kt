package project.api.app.Selection

import org.springframework.data.jpa.repository.JpaRepository
import project.api.app.Selection.data.SelectionProcess
import project.api.app.candidates.data.Candidate

interface SelectionProcessRepository: JpaRepository<SelectionProcess, Int> {

//    fun findByCandidate(candidate: Candidate): List<SelectionProcess>
fun findTopByCandidateAndProgressLessThanOrderByCreatedAtDesc(candidate: Candidate, progress: Double): SelectionProcess?

//    fun findByCandidateOrderByCreatedAtDesc(candidate: Candidate): SelectionProcess?
}