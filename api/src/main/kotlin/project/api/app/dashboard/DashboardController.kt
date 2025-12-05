package project.api.app.dashboard

import org.springframework.web.bind.annotation.GetMapping
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
}
