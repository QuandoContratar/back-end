//package project.api.app.dashboard
//
//import jakarta.persistence.EntityManager
//import jakarta.persistence.PersistenceContext
//import org.springframework.data.jpa.repository.Query
//import org.springframework.stereotype.Repository
//
//@Repository
//class DashboardRepository {
//
//    @PersistenceContext
//    lateinit var em: EntityManager
//
//    fun countTotalVagas(): Long =
//        em.createNativeQuery("SELECT COUNT(*) FROM vacancies")
//            .singleResult.toString().toLong()
//
//    fun countTotalCandidatos(): Long =
//        em.createNativeQuery("SELECT COUNT(*) FROM candidate")
//            .singleResult.toString().toLong()
//
//    fun countVagasAbertas(): Long =
//        em.createNativeQuery("SELECT COUNT(*) FROM vacancies WHERE status_vacancy = 'aberta'")
//            .singleResult.toString().toLong()
//
//    fun taxaConversao(): Double =
//        em.createNativeQuery("""
//            SELECT
//                (SELECT COUNT(*) FROM selection_process WHERE outcome = 'aprovado') /
//                (SELECT COUNT(*) FROM candidate) * 100
//        """)
//            .singleResult.toString().toDouble()
//
//    fun vagasPorMes(): List<Array<Any>> =
//        em.createNativeQuery("""
//            SELECT MONTH(created_at) AS mes, COUNT(*) AS quantidade
//            FROM vacancies
//            GROUP BY MONTH(created_at)
//        """).resultList.map { it as Array<Any> }
//
//    fun statusVagas(): List<Array<Any>> =
//        em.createNativeQuery("""
//            SELECT status_vacancy, COUNT(*)
//            FROM vacancies
//            GROUP BY status_vacancy
//        """).resultList.map { it as Array<Any> }
//
//    fun candidatosPorVaga(): List<Array<Any>> =
//        em.createNativeQuery("""
//            SELECT v.position_job, COUNT(c.id_candidate)
//            FROM vacancies v
//            LEFT JOIN candidate c ON c.vacancy_id = v.id_vacancy
//            GROUP BY v.id_vacancy
//        """).resultList.map { it as Array<Any> }
//
//    fun tipoContrato(): List<Array<Any>> =
//        em.createNativeQuery("""
//            SELECT contract_type, COUNT(*)
//            FROM vacancies
//            GROUP BY contract_type
//        """).resultList.map { it as Array<Any> }
//
//    fun tempoPreenchimento(): List<Array<Any>> =
//        em.createNativeQuery("""
//            SELECT
//                MONTH(v.created_at),
//                AVG(DATEDIFF(sp.created_at, v.created_at))
//            FROM vacancies v
//            JOIN selection_process sp ON sp.fk_vacancy = v.id_vacancy
//            WHERE sp.outcome = 'aprovado'
//            GROUP BY MONTH(v.created_at)
//        """).resultList.map { it as Array<Any> }
//
//}


package project.api.app.dashboard

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class DashboardRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun countTotalVagas(): Long =
        (em.createNativeQuery("SELECT COUNT(*) FROM vacancies").singleResult as Number).toLong()

    fun countTotalCandidatos(): Long =
        (em.createNativeQuery("SELECT COUNT(*) FROM candidate").singleResult as Number).toLong()

    fun countVagasAbertas(): Long =
        (em.createNativeQuery("SELECT COUNT(*) FROM vacancies WHERE status_vacancy = 'aberta'")
            .singleResult as Number).toLong()

    fun taxaConversao(): Double =
        (em.createNativeQuery("""
            SELECT 
                (SELECT COUNT(*) FROM selection_process WHERE outcome = 'aprovado') /
                (SELECT COUNT(*) FROM candidate) * 100
        """).singleResult as Number).toDouble()

    fun vagasPorMes(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT MONTH(created_at), COUNT(*)
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
            SELECT MONTH(v.created_at), AVG(DATEDIFF(sp.created_at, v.created_at))
            FROM vacancies v
            JOIN selection_process sp ON sp.fk_vacancy = v.id_vacancy
            WHERE sp.outcome = 'aprovado'
            GROUP BY MONTH(v.created_at)
        """).resultList.map { it as Array<Any> }

    // ============================================================
    // NOVOS GRÁFICOS COM ENTITY MANAGER
    // ============================================================

    fun countCandidatesByState(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT c.state, COUNT(*)
            FROM candidate c
            WHERE c.status = 'ativo'
            GROUP BY c.state
        """).resultList.map { it as Array<Any> }

    fun countMatchLevelDistribution(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT cm.match_level, COUNT(*)
            FROM candidate_match cm
            GROUP BY cm.match_level
        """).resultList.map { it as Array<Any> }

    fun findTopCandidatesByVacancy(vacancyId: Int): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT 
                cm.id_match,
                c.id_candidate,
                c.name AS candidateName,
                v.id_vacancy,
                v.position_job,
                cm.score,
                cm.match_level
            FROM candidate_match cm
            JOIN candidate c ON c.id_candidate = cm.fk_candidate
            JOIN vacancies v ON v.id_vacancy = cm.fk_vacancy
            WHERE v.id_vacancy = :vacancyId
            ORDER BY cm.score DESC
            LIMIT 10
        """)
            .setParameter("vacancyId", vacancyId)
            .resultList.map { it as Array<Any> }

    fun countCandidatesByStage(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT ks.name, COUNT(*)
            FROM kanban_card kc
            JOIN kanban_stage ks ON ks.id_stage = kc.fk_stage
            GROUP BY ks.id_stage, ks.name
            ORDER BY ks.position_order
        """).resultList.map { it as Array<Any> }

    fun avgTimeByStage(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT 
                ks.name,
                AVG(
                    CASE 
                        WHEN kc.updated_at IS NOT NULL 
                        THEN TIMESTAMPDIFF(DAY, kc.created_at, kc.updated_at)
                        ELSE TIMESTAMPDIFF(DAY, kc.created_at, NOW())
                    END
                )
            FROM kanban_card kc
            JOIN kanban_stage ks ON ks.id_stage = kc.fk_stage
            GROUP BY ks.id_stage, ks.name
            ORDER BY ks.position_order
        """).resultList.map { it as Array<Any> }

    fun countVacanciesByStatus(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT v.status_vacancy, COUNT(*)
            FROM vacancies v
            GROUP BY v.status_vacancy
        """).resultList.map { it as Array<Any> }

    fun managerPerformance(): List<Array<Any>> =
        em.createNativeQuery("""
            SELECT 
                u.id_user,
                u.name,
                COUNT(v.id_vacancy),
                SUM(CASE WHEN v.status_vacancy = 'aberta' THEN 1 ELSE 0 END),
                SUM(CASE WHEN v.status_vacancy = 'rejeitada' THEN 1 ELSE 0 END),
                AVG(
                    CASE 
                        WHEN v.status_vacancy = 'aberta' 
                        THEN DATEDIFF(CURDATE(), v.created_at)
                        ELSE NULL
                    END
                )
            FROM user u
            JOIN vacancies v ON v.fk_manager = u.id_user
            GROUP BY u.id_user, u.name
        """).resultList.map { it as Array<Any> }
}

