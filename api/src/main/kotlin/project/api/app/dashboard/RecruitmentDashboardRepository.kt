package project.api.app.dashboard

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class RecruitmentDashboardRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun vagasPorArea(area: String): List<Array<Any>> =
        em.createNativeQuery(
            """
            SELECT 
                v.id_vacancy,
                v.position_job,
                COUNT(cm.fk_candidate)
            FROM vacancies v
            LEFT JOIN candidate_match cm ON cm.fk_vacancy = v.id_vacancy
            WHERE v.area = :area
            GROUP BY v.id_vacancy
        """
        )
            .setParameter("area", area)
            .resultList.map { it as Array<Any> }

    fun kpis(vagaId: Int): List<Array<Any>> =
        em.createNativeQuery(
            """
            SELECT 
                SUM(CASE WHEN ks.name IN ('aguardando_triagem','triagem') THEN 1 ELSE 0 END),
                SUM(CASE WHEN ks.name = 'teste_tecnico' THEN 1 ELSE 0 END),
                SUM(CASE WHEN ks.name = 'avaliacao_fit_cultural' THEN 1 ELSE 0 END),
                SUM(CASE WHEN ks.name = 'proposta_fechamento' THEN 1 ELSE 0 END)
            FROM kanban_card kc
            JOIN kanban_stage ks ON ks.id_stage = kc.fk_stage
            WHERE kc.fk_vacancy = :id
        """
        )
            .setParameter("id", vagaId)
            .resultList.map { it as Array<Any> }

    fun candidatosDaVaga(vagaId: Int, match: String): List<Array<Any>> =
        em.createNativeQuery(
            """
            SELECT 
                c.id_candidate,
                c.name,
                v.contract_type,
                v.period,
                v.work_model,
                v.location,
                u.name AS gestor,
                cm.score
            FROM candidate_match cm
            JOIN candidate c ON c.id_candidate = cm.fk_candidate
            JOIN vacancies v ON v.id_vacancy = cm.fk_vacancy
            LEFT JOIN user u ON u.id_user = v.fk_manager
            WHERE cm.fk_vacancy = :vaga
              AND (:match = 'all' OR cm.match_level = UPPER(:match))
            ORDER BY cm.score DESC
        """
        )
            .setParameter("vaga", vagaId)
            .setParameter("match", match)
            .resultList.map { it as Array<Any> }

    fun retencao(vagaId: Int): List<Array<Any>> =
        em.createNativeQuery(
            """
            SELECT
                SUM(CASE WHEN ks.name IN (
                    'aguardando_triagem','triagem','entrevista_rh',
                    'avaliacao_fit_cultural','teste_tecnico','entrevista_final'
                ) THEN 1 ELSE 0 END),
                SUM(CASE WHEN sp.outcome = 'reprovado' THEN 1 ELSE 0 END),
                SUM(CASE WHEN ks.name IN ('proposta_fechamento','contratacao') THEN 1 ELSE 0 END)
            FROM kanban_card kc
            JOIN kanban_stage ks ON ks.id_stage = kc.fk_stage
            LEFT JOIN selection_process sp 
                ON sp.fk_candidate = kc.fk_candidate 
               AND sp.fk_vacancy = kc.fk_vacancy
            WHERE kc.fk_vacancy = :vaga
        """
        )
            .setParameter("vaga", vagaId)
            .resultList.map { it as Array<Any> }

    fun ocupacao(vagaId: Int): List<Array<Any>> =
        em.createNativeQuery(
            """
            SELECT 
                v.total_positions,
                SUM(CASE WHEN ks.name IN ('contratacao','proposta_fechamento') THEN 1 ELSE 0 END)
            FROM vacancies v
            LEFT JOIN kanban_card kc ON kc.fk_vacancy = v.id_vacancy
            LEFT JOIN kanban_stage ks ON ks.id_stage = kc.fk_stage
            WHERE v.id_vacancy = :id
            GROUP BY v.id_vacancy
        """
        )
            .setParameter("id", vagaId)
            .resultList.map { it as Array<Any> }
}
