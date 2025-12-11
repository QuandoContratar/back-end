package project.api.app.dashboard


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dashboard/recruitment")
class RecruitmentDashboardController(
    private val service: RecruitmentDashboardService
) {

    // 1) Tela da Área — vagas + total de candidatos
    @GetMapping("/area/{area}/vagas")
    fun vagasPorArea(@PathVariable area: String): List<AreaVacancyDTO> =
        service.getVagasPorArea(area)

    // 2) KPIs da vaga
    @GetMapping("/vaga/{vagaId}/kpis")
    fun kpisDaVaga(@PathVariable vagaId: Int): VacancyKPIsDTO =
        service.getKPIs(vagaId)

    // 3) Lista de candidatos da vaga com filtro de match
    @GetMapping("/vaga/{vagaId}/candidatos")
    fun candidatosDaVaga(
        @PathVariable vagaId: Int,
        @RequestParam(defaultValue = "all") match: String
    ): List<VacancyCandidateDTO> =
        service.getCandidatosDaVaga(vagaId, match)

    // 4) Gráfico de retenção
    @GetMapping("/vaga/{vagaId}/retencao")
    fun retencao(@PathVariable vagaId: Int): VacancyRetentionDTO =
        service.getRetencao(vagaId)

    // 5) Ocupação / vagas restantes
    @GetMapping("/vaga/{vagaId}/ocupacao")
    fun ocupacao(@PathVariable vagaId: Int): VacancyOcupacaoDTO =
        service.getOcupacao(vagaId)
}
