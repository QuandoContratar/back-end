package project.api.app.vacancies

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancyOpeningDTO
import project.api.app.vacancies.data.VacancySummaryDTO

@Repository
interface VacancyRepository : JpaRepository<Vacancy, Int> {

    @Query("""
    SELECT v.id, v.position_job, v.workModel, v.manager.name, v.area
    FROM Vacancy v WHERE v.statusVacancy = 'aberta'
""")
    fun findActiveVacancies(): List<Array<Any?>>
    // Busca pelo ID do manager (User tem id do tipo Int)
    @Query("SELECT v FROM Vacancy v WHERE v.manager.id = :managerId")
    fun findByManagerId(managerId: Int): List<Vacancy>

    fun findByStatusVacancy(status: String): List<Vacancy>

    @Query("""
    SELECT
        v.position_job,
        v.period,
        v.workModel,
        v.requirements,
        v.contractType,
        v.salary,
        v.location,
        v.area,
        v.openingJustification,
        v.statusVacancy
    FROM Vacancy v
    WHERE v.statusVacancy = 'pendente_aprovacao'
""")
    fun findOpeningVacancies(): List<Array<Any?>>

//    @Query(
//        value = "SELECT COUNT(*) FROM vacancies",
//        nativeQuery = true
//    )
//    fun countTotalVagas(): Long
//
//    @Query(
//        value = "SELECT COUNT(*) FROM vacancies WHERE status_vacancy = 'aberta'",
//        nativeQuery = true
//    )
//    fun countVagasAbertas(): Long
//
//    @Query("""
//   SELECT MONTH(created_at) AS mes, COUNT(*) AS quantidade
//   FROM vacancies
//   GROUP BY MONTH(created_at)
//   ORDER BY mes
//""", nativeQuery = true)
//    fun vagasPorMes(): List<Array<Any>>
//
//
//    @Query("""
//   SELECT status_vacancy, COUNT(*)
//   FROM vacancies
//   GROUP BY status_vacancy
//""", nativeQuery = true)
//    fun vagasPorStatus(): List<Array<Any>>
//
//
//    @Query("""
//   SELECT contract_type, COUNT(*)
//   FROM vacancies
//   GROUP BY contract_type
//""", nativeQuery = true)
//    fun tipoContrato(): List<Array<Any>>

}