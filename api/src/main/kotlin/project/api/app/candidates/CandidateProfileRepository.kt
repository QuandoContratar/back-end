package project.api.app.candidates


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import project.api.app.candidates.data.Candidate
import project.api.app.candidates.data.CandidateProfile

@Repository
interface CandidateProfileRepository : JpaRepository<CandidateProfile, Int> {

    fun findByCandidate(candidate: Candidate): CandidateProfile?
    fun findByCandidateId(idCandidate: Int): CandidateProfile?

}
