package project.api.app.reports

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class ReportsRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun kpiQuantidadeDeVagas(area: String) =
        em.createNativeQuery("""SELECT COUNT(id_vacancy)
FROM vacancies
WHERE area = :area;""")
            .setParameter("area", area)
            .singleResult

    fun kpiQuantidadeDeCandidatos(area: String) =
        em.createNativeQuery("""SELECT COUNT(id_match)
FROM candidate_match
INNER JOIN vacancies as tbl_vagas ON candidate_match.fk_vacancy = tbl_vagas.id_vacancy
WHERE tbl_vagas.area = :area;""")
            .setParameter("area", area)
            .singleResult

    fun graficoDeBarrasEmpilhado(area: String) =
        em.createNativeQuery("""SELECT
    v.position_job AS vaga,
    SUM(CASE WHEN cm.match_level = 'BAIXO' THEN 1 ELSE 0 END) AS baixo,
    SUM(CASE WHEN cm.match_level = 'MEDIO' THEN 1 ELSE 0 END) AS medio,
    SUM(CASE WHEN cm.match_level = 'ALTO' THEN 1 ELSE 0 END) AS alto,
    SUM(CASE WHEN cm.match_level = 'DESTAQUE' THEN 1 ELSE 0 END) AS destaque,
    COUNT(cm.fk_candidate) AS total_candidatos
FROM vacancies v
LEFT JOIN candidate_match cm
    ON v.id_vacancy = cm.fk_vacancy
WHERE v.area = :area
GROUP BY v.id_vacancy, v.position_job
ORDER BY v.id_vacancy;""")
            .setParameter("area", area)
            .resultList

    fun kpisQuantidadeDePessoasPorMatch(area: String) =
        em.createNativeQuery("""SELECT 
    SUM(CASE WHEN cm.match_level = 'BAIXO' THEN 1 ELSE 0 END) AS Baixo,
    SUM(CASE WHEN cm.match_level = 'MEDIO' THEN 2 ELSE 0 END) AS Medio,
    SUM(CASE WHEN cm.match_level = 'ALTO' THEN 3 ELSE 0 END) AS Alto,
    SUM(CASE WHEN cm.match_level = 'DESTAQUE' THEN 4 ELSE 0 END) AS Destaque
FROM candidate_match cm
JOIN vacancies v ON cm.fk_vacancy = v.id_vacancy
WHERE v.area = :area""")
            .setParameter("area", area)
            .resultList

    fun kpisDeTaxaDeAprovacao(area: String) =
        em.createNativeQuery("""SELECT 
    v.area,
    ROUND((SUM(CASE WHEN sp.current_stage = 'triagem' THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS taxa_triagem,
    ROUND((SUM(CASE WHEN sp.current_stage = 'teste_tecnico' THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS taxa_teste_tecnico,
    ROUND((SUM(CASE WHEN sp.current_stage = 'entrevista_tecnica' THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS taxa_entrevista_tecnica,
    ROUND((SUM(CASE WHEN sp.current_stage = 'contratacao' THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) AS taxa_contratacao
FROM selection_process sp
INNER JOIN vacancies v 
    ON v.id_vacancy = sp.fk_vacancy
WHERE v.area = :area
GROUP BY v.area;""")
            .setParameter("area", area)
            .resultList

    fun graficoDeBarrasDeTaxaDeContratacaoPorArea() =
        em.createNativeQuery("""SELECT 
    v.area,
    -- Total de currículos relacionados à área (baseado em candidate_match)
    COUNT(cm.fk_candidate) AS total_candidatos, 
    -- Total de candidatos aceitos
    SUM(CASE WHEN cm.candidate_status = 'DESTAQUE' THEN 1 ELSE 0 END) AS total_contratados,
    -- Taxa %
    ROUND(
        (SUM(CASE WHEN cm.candidate_status = 'DESTAQUE' THEN 1 ELSE 0 END) 
        / COUNT(cm.fk_candidate)) * 100,
        2
    ) AS taxa_contratacao
FROM candidate_match cm
INNER JOIN vacancies v 
    ON v.id_vacancy = cm.fk_vacancy
GROUP BY v.area;""")
            .resultList

    fun rankingMelhoresFaculdades(area: String) =
        em.createNativeQuery("""SELECT 
    institution,
    COUNT(*) AS total_candidates,
    ROW_NUMBER() OVER (ORDER BY COUNT(*) DESC, institution ASC) AS rank_faculty
FROM vacancies
WHERE institution IN ('USP', 'SPTECH', 'FIAP') AND area = :area
GROUP BY institution
ORDER BY rank_faculty
LIMIT 3;""")
            .setParameter("area", area)
            .resultList
}