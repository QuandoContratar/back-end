package project.api.app.match


import org.springframework.data.jpa.repository.JpaRepository

interface CandidateMatchRepository : JpaRepository<CandidateMatch, Int> {
    fun findByVacancyId(vacancyId: Int): List<CandidateMatch>
    fun findByCandidate_idCandidate(candidateId: Int): List<CandidateMatch>

//    @Query("SELECT m FROM CandidateMatch m WHERE m.vacancy.id_vacancy = :vacancyId")
//    fun findByVacancyId(@Param("vacancyId") vacancyId: Int): List<CandidateMatch>
//
//    @Query("SELECT m FROM CandidateMatch m WHERE m.candidate.id_candidate = :candidateId")
//    fun findByCandidateId(@Param("idCandidate") candidateId: Int): List<CandidateMatch>

}
