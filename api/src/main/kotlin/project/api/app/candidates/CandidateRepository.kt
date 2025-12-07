package project.api.app.candidates

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import project.api.app.candidates.data.Candidate


@Repository
interface CandidateRepository:JpaRepository<Candidate, Int>{
    fun findByCurrentStage(stage: String): List<Candidate>

    fun findByVacancyId(vacancyId: Long): List<Candidate>

    fun findByStatus(status: String): List<Candidate>
//
//    @Query(
//        value = "SELECT COUNT(*) FROM candidate",
//        nativeQuery = true
//    )
//    fun countTotalCandidatos(): Long
//
//    @Query("""
//   SELECT v.position_job, COUNT(c.id_candidate) AS total
//   FROM candidate c
//   JOIN vacancies v ON c.fk_vacancy = v.id_vacancy
//   GROUP BY v.id_vacancy
//""", nativeQuery = true)
//    fun candidatosPorVaga(): List<Array<Any>>

}