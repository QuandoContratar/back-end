package project.api.app.reports

import org.springframework.stereotype.Service

@Service
class ReportsService(
    private val repository: ReportsRepository
) {
    fun kpiQuantidadeDeVagas(area: String) = repository.kpiQuantidadeDeVagas(area)

    fun kpiQuantidadeDeCandidatos(area: String) = repository.kpiQuantidadeDeCandidatos(area)

    fun graficoDeBarrasEmpilhado(area: String) = repository.graficoDeBarrasEmpilhado(area)

    fun kpisQuantidadeDePessoasPorMatch(area: String) = repository.kpisQuantidadeDePessoasPorMatch(area)

    fun kpisDeTaxaDeAprovacao(area: String) = repository.kpisDeTaxaDeAprovacao(area)

    fun graficoDeBarrasDeTaxaDeContratacaoPorArea() = repository.graficoDeBarrasDeTaxaDeContratacaoPorArea()

    fun rankingMelhoresFaculdades(area: String) = repository.rankingMelhoresFaculdades(area)
}