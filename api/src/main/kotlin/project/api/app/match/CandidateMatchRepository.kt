package project.api.app.match


import org.springframework.data.jpa.repository.JpaRepository

interface CandidateMatchRepository : JpaRepository<CandidateMatch, Int> {
    fun findByVacancyId(vacancyId: Int): List<CandidateMatch>
    fun findByCandidateId(candidateId: Int): List<CandidateMatch>
}
