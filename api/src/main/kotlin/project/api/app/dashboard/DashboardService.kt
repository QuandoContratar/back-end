//package project.api.app.dashboard
//
//import org.springframework.stereotype.Service
//import project.api.app.candidates.CandidateRepository
//import project.api.app.kanban.KanbanCardRepository
//import project.api.app.vacancies.VacancyRepository
//
//
//@Service
//class DashboardService(
//    private val vacanciesRepository: VacancyRepository,
//    private val candidateRepository: CandidateRepository,
//    private val kanbanRepository: KanbanCardRepository
//) {
//
//
//    fun getMetrics(): DashboardMetricsDTO {
//        val totalVagas = vacanciesRepository.countTotalVagas()
//        val totalCandidatos = candidateRepository.countTotalCandidatos()
//        val vagasAbertas = vacanciesRepository.countVagasAbertas()
//        val contratados = kanbanRepository.countContratados()
//
//
//        val taxa = if (totalCandidatos == 0L) 0.0 else (contratados.toDouble() / totalCandidatos) * 100
//
//
//        return DashboardMetricsDTO(
//            totalVagas = totalVagas,
//            totalCandidatos = totalCandidatos,
//            vagasAbertas = vagasAbertas,
//            taxaConversao = taxa
//        )
//    }
//
//
//    fun getVagasPorMes(): List<VagasPorMesDTO> =
//        vacanciesRepository.vagasPorMes().map {
//            VagasPorMesDTO(
//                mes = (it[0] as Number).toInt(),
//                quantidade = (it[1] as Number).toLong()
//            )
//        }
//
//
//    fun getStatusVagas(): List<StatusVagasDTO> =
//        vacanciesRepository.vagasPorStatus().map {
//            StatusVagasDTO(
//                status = it[0].toString(),
//                quantidade = (it[1] as Number).toLong()
//            )
//        }
//
//
//    fun getCandidatosPorVaga(): List<CandidatosPorVagaDTO> =
//        candidateRepository.candidatosPorVaga().map {
//            CandidatosPorVagaDTO(
//                vaga = it[0].toString(),
//                totalCandidatos = (it[1] as Number).toLong()
//            )
//        }
//
//
//    fun getTipoContrato(): List<TipoContratoDTO> =
//        vacanciesRepository.tipoContrato().map {
//            TipoContratoDTO(
//                contrato = it[0].toString(),
//                total = (it[1] as Number).toLong()
//            )
//        }
//}


package project.api.app.dashboard

import org.springframework.stereotype.Service

@Service
class DashboardService(
    private val repo: DashboardRepository
) {

    fun getMetrics(): Map<String, Any> {
        val totalVagas = repo.countTotalVagas()
        val totalCandidatos = repo.countTotalCandidatos()
        val vagasAbertas = repo.countVagasAbertas()

        val taxa = repo.taxaConversao() ?: 0.0

        return mapOf(
            "totalVagas" to totalVagas,
            "totalCandidatos" to totalCandidatos,
            "vagasAbertas" to vagasAbertas,
            "taxaConversao" to taxa
        )
    }

    fun getVagasPorMes() =
        repo.vagasPorMes().map {
            mapOf("mes" to (it[0] as Number).toInt(), "quantidade" to (it[1] as Number).toInt())
        }

    fun getStatusVagas() =
        repo.statusVagas().map {
            mapOf("status" to it[0].toString(), "quantidade" to (it[1] as Number).toInt())
        }

    fun getCandidatosPorVaga() =
        repo.candidatosPorVaga().map {
            mapOf("vaga" to it[0].toString(), "totalCandidatos" to (it[1] as Number).toInt())
        }

    fun getTipoContrato() =
        repo.tipoContrato().map {
            mapOf("contrato" to it[0].toString(), "total" to (it[1] as Number).toInt())
        }

    fun getTempoPreenchimento() =
        repo.tempoPreenchimento().map {
            mapOf("mes" to (it[0] as Number).toInt(), "dias" to (it[1] as Number).toInt())
        }
}
