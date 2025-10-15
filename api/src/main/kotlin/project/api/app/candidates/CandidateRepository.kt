package project.api.app.candidates

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import project.api.app.candidates.data.Candidate


@Repository
interface CandidateRepository:JpaRepository<Candidate, Int>{

}