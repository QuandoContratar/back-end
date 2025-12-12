package project.api.app.reports

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reports")
class ReportsController (
    private val service: ReportsService
) {

    @GetMapping("/kpi-quantidade-de-vagas")
    fun kpiQuantidadeDeVagas(@org.springframework.web.bind.annotation.RequestParam area: String) = service.kpiQuantidadeDeVagas(area)


    @GetMapping("/kpi-quantidade-de-candidatos")
    fun kpiQuantidadeDeCandidatos(@org.springframework.web.bind.annotation.RequestParam area: String) = service.kpiQuantidadeDeCandidatos(area)


    @GetMapping("/grafico-de-barras-empilhado")
    fun graficoDeBarrasEmpilhado(@org.springframework.web.bind.annotation.RequestParam area: String) = service.graficoDeBarrasEmpilhado(area)


    @GetMapping("/kpis-quantidade-de-pessoas-por-match")
    fun kpisQuantidadeDePessoasPorMatch(@org.springframework.web.bind.annotation.RequestParam area: String) = service.kpisQuantidadeDePessoasPorMatch(area)


    @GetMapping("/kpis-de-taxa-de-aprovacao")
    fun kpisDeTaxaDeAprovacao(@org.springframework.web.bind.annotation.RequestParam area: String) = service.kpisDeTaxaDeAprovacao(area)


    @GetMapping("/grafico-de-barras-de-taxa-de-contratacao-por-area")
    fun graficoDeBarrasDeTaxaDeContratacaoPorArea() = service.graficoDeBarrasDeTaxaDeContratacaoPorArea()


    @GetMapping("/ranking-das-melhores-faculdades")
    fun rankingMelhoresFaculdades(@org.springframework.web.bind.annotation.RequestParam area: String) = service.rankingMelhoresFaculdades(area)
}