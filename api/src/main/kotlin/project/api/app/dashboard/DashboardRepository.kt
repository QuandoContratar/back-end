package project.api.app.dashboard

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class DashboardRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun countTotalVagas(): Long =
        em.createNativeQuery("SELECT COUNT(*) FROM vacancies")
            .singleResult.toString().toLong()

    fun countTotalCandidatos(): Long =
        em.createNativeQuery("SELECT COUNT(*) FROM candidate")
            .singleResult.toString().toLong()

    fun countVagasAbertas(): Long =
        em.createNativeQuery("SELECT COUNT(*) FROM vacancies WHERE status_vacancy = 'aberta'")
            .singleResult.toString().toLong()

    fun taxaConversao(): Double =
        em.createNativeQuery("""
            SELECT 
                (SELECT COUNT(*) FROM selection_process WHERE outcome = 'aprovado') /
                (SELECT COUNT(*) FROM candidate) * 100
        """)
            .singleResult.toString().toDouble()

    fun vagasPorMes(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT MONTH(created_at) AS mes, COUNT(*) AS quantidade
            FROM vacancies
            GROUP BY MONTH(created_at)
        """).resultList.map { it as Array<Any> }

    fun statusVagas(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT status_vacancy, COUNT(*) 
            FROM vacancies
            GROUP BY status_vacancy
        """).resultList.map { it as Array<Any> }

    fun candidatosPorVaga(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT v.position_job, COUNT(c.id_candidate)
            FROM vacancies v
            LEFT JOIN candidate c ON c.vacancy_id = v.id_vacancy
            GROUP BY v.id_vacancy
        """).resultList.map { it as Array<Any> }

    fun tipoContrato(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT contract_type, COUNT(*)
            FROM vacancies
            GROUP BY contract_type
        """).resultList.map { it as Array<Any> }

    fun tempoPreenchimento(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT 
                MONTH(v.created_at),
                AVG(DATEDIFF(sp.created_at, v.created_at))
            FROM vacancies v
            JOIN selection_process sp ON sp.fk_vacancy = v.id_vacancy
            WHERE sp.outcome = 'aprovado'
            GROUP BY MONTH(v.created_at)
        """).resultList.map { it as Array<Any> }
}
