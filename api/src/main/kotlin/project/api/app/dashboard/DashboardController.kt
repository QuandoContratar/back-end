package project.api.app.dashboard

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dashboard")
class DashboardController(
    private val service: DashboardService
) {

    @GetMapping("/metrics")
    fun metrics() = service.getMetrics()

    @GetMapping("/vagas-mes")
    fun vagasMes() = service.getVagasPorMes()

    @GetMapping("/status-vagas")
    fun statusVagas() = service.getStatusVagas()

    @GetMapping("/candidatos-vaga")
    fun candidatosVaga() = service.getCandidatosPorVaga()

    @GetMapping("/tipo-contrato")
    fun tipoContrato() = service.getTipoContrato()

    @GetMapping("/skills/hard")
    fun hardSkills(): List<HardSkillDTO> =
        service.getHardSkills()

    @GetMapping("/skills/soft")
    fun softSkills(): List<SoftSkillDTO> =
        service.getSoftSkills()

    @GetMapping("/recommendation/{candidateId}")
    fun recommendation(@PathVariable candidateId: Int): List<VacancyRecommendationDTO> =
        service.recommendVacancies(candidateId)

    @GetMapping("/first-contact")
    fun firstContact(): List<FirstContactDTO> =
        service.getFirstContactTimes()

    // 1) Candidatos por estado (mapa / barras)
        @GetMapping("/candidates-by-state")
        fun candidatesByState(): List<CandidatesByStateDTO> =
            service.getCandidatesByState()

        // 2) Distribuição de match (pizza / doughnut / radar)
        @GetMapping("/match-distribution")
        fun matchDistribution(): List<MatchLevelDistributionDTO> =
            service.getMatchLevelDistribution()

        // 3) Top 10 candidatos por score de match por vaga
        @GetMapping("/top-candidates/{vacancyId}")
        fun topCandidatesByVacancy(@PathVariable vacancyId: Int): List<TopCandidateMatchDTO> =
            service.getTopCandidatesByVacancy(vacancyId)

        // 4) Pipeline: quantidade por etapa
        @GetMapping("/pipeline-by-stage")
        fun pipelineByStage(): List<StageCountDTO> =
            service.getCandidatesByStage()

        // 5) Tempo médio por etapa
        @GetMapping("/avg-time-by-stage")
        fun avgTimeByStage(): List<StageAvgTimeDTO> =
            service.getAvgTimeByStage()

        // 6) Status geral das vagas
        @GetMapping("/vacancies-status-summary")
        fun vacanciesStatusSummary(): List<VacancyStatusSummaryDTO> =
            service.getVacancyStatusSummary()

        // 7) Performance dos gestores
        @GetMapping("/manager-performance")
        fun managerPerformance(): List<ManagerPerformanceDTO> =
            service.getManagerPerformance()
    }
