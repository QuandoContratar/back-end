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
    private val repo: DashboardRepository,

) {

    // ======================================================
    // MÉTRICAS GERAIS
    // ======================================================
    fun getMetrics(): DashboardMetricsDTO {
        val totalVagas = repo.countTotalVagas()
        val totalCandidatos = repo.countTotalCandidatos()
        val vagasAbertas = repo.countVagasAbertas()
        val taxaConversao = repo.taxaConversao()

        return DashboardMetricsDTO(
            totalVagas = totalVagas,
            totalCandidatos = totalCandidatos,
            vagasAbertas = vagasAbertas,
            taxaConversao = taxaConversao
        )
    }

    // ======================================================
    // VAGAS POR MÊS
    // ======================================================
    fun getVagasPorMes(): List<VagasPorMesDTO> =
        repo.vagasPorMes().map { row ->
            VagasPorMesDTO(
                mes = (row[0] as Number).toInt(),
                quantidade = (row[1] as Number).toInt()
            )
        }

    // ======================================================
    // STATUS DAS VAGAS
    // ======================================================
    fun getStatusVagas(): List<StatusVagasDTO> =
        repo.statusVagas().map { row ->
            StatusVagasDTO(
                status = row[0].toString(),
                quantidade = (row[1] as Number).toInt()
            )
        }

    // ======================================================
    // CANDIDATOS POR VAGA
    // ======================================================
    fun getCandidatosPorVaga(): List<CandidatosPorVagaDTO> =
        repo.candidatosPorVaga().map { row ->
            CandidatosPorVagaDTO(
                vaga = row[0].toString(),
                totalCandidatos = (row[1] as Number).toInt()
            )
        }

    // ======================================================
    // TIPO DE CONTRATO
    // ======================================================
    fun getTipoContrato(): List<TipoContratoDTO> =
        repo.tipoContrato().map { row ->
            TipoContratoDTO(
                contrato = row[0].toString(),
                total = (row[1] as Number).toInt()
            )
        }

    // ======================================================
    // TEMPO DE PREENCHIMENTO DE VAGAS
    // ======================================================
    fun getTempoPreenchimento(): List<TempoMedioPreenchimentoDTO> =
        repo.tempoPreenchimento().map { row ->
            TempoMedioPreenchimentoDTO(
                mes = (row[0] as Number).toInt(),
                dias = (row[1] as Number).toInt()
            )
        }

    // ======================================================
    // 1) CANDIDATOS POR ESTADO
    // ======================================================
    fun getCandidatesByState(): List<CandidatesByStateDTO> =
        repo.countCandidatesByState().map { row ->
            CandidatesByStateDTO(
                state = row[0]?.toString() ?: "N/A",
                total = (row[1] as Number).toLong()
            )
        }

    // ======================================================
    // 2) DISTRIBUIÇÃO DO MATCH (PIZZA)
    // ======================================================
    fun getMatchLevelDistribution(): List<MatchLevelDistributionDTO> =
        repo.countMatchLevelDistribution().map { row ->
            MatchLevelDistributionDTO(
                matchLevel = row[0]?.toString() ?: "N/A",
                total = (row[1] as Number).toLong()
            )
        }

    // ======================================================
    // 3) TOP 10 CANDIDATOS POR SCORE PARA UMA VAGA
    // ======================================================
    fun getTopCandidatesByVacancy(vacancyId: Int): List<TopCandidateMatchDTO> =
        repo.findTopCandidatesByVacancy(vacancyId).map { row ->
            TopCandidateMatchDTO(
                matchId = (row[0] as Number).toInt(),
                candidateId = (row[1] as Number).toInt(),
                candidateName = row[2].toString(),
                vacancyId = (row[3] as Number).toInt(),
                vacancyPosition = row[4].toString(),
                score = (row[5] as Number).toDouble(),
                matchLevel = row[6].toString()
            )
        }

    // ======================================================
    // 4) CANDIDATOS POR ETAPA DO PIPELINE (FUNIL)
    // ======================================================
    fun getCandidatesByStage(): List<StageCountDTO> =
        repo.countCandidatesByStage().map { row ->
            StageCountDTO(
                stageName = row[0].toString(),
                total = (row[1] as Number).toLong()
            )
        }

    // ======================================================
    // 5) TEMPO MÉDIO POR ETAPA
    // ======================================================
    fun getAvgTimeByStage(): List<StageAvgTimeDTO> =
        repo.avgTimeByStage().map { row ->
            StageAvgTimeDTO(
                stageName = row[0].toString(),
                avgDays = (row[1] as Number).toDouble()
            )
        }

    // ======================================================
    // 6) STATUS GERAL DAS VAGAS (RESUMO)
    // ======================================================
    fun getVacancyStatusSummary(): List<VacancyStatusSummaryDTO> =
        repo.countVacanciesByStatus().map { row ->
            VacancyStatusSummaryDTO(
                status = row[0].toString(),
                total = (row[1] as Number).toLong()
            )
        }

    // ======================================================
    // 7) PERFORMANCE DOS GESTORES
    // ======================================================
    fun getManagerPerformance(): List<ManagerPerformanceDTO> =
        repo.managerPerformance().map { row ->
            ManagerPerformanceDTO(
                managerId = (row[0] as Number).toInt(),
                managerName = row[1].toString(),
                totalVacancies = (row[2] as Number).toLong(),
                approvedVacancies = (row[3] as Number).toLong(),
                rejectedVacancies = (row[4] as Number).toLong(),
                avgApprovalDays = (row[5] as? Number)?.toDouble() // pode ser null
            )
        }
}

//
//fun getMetrics(): Map<String, Any> {
//    val totalVagas = repo.countTotalVagas()
//    val totalCandidatos = repo.countTotalCandidatos()
//    val vagasAbertas = repo.countVagasAbertas()
//
//    val taxa = repo.taxaConversao() ?: 0.0
//
//    return mapOf(
//        "totalVagas" to totalVagas,
//        "totalCandidatos" to totalCandidatos,
//        "vagasAbertas" to vagasAbertas,
//        "taxaConversao" to taxa
//    )
//}
//
//fun getVagasPorMes() =
//    repo.vagasPorMes().map {
//        mapOf("mes" to (it[0] as Number).toInt(), "quantidade" to (it[1] as Number).toInt())
//    }
//
//fun getStatusVagas() =
//    repo.statusVagas().map {
//        mapOf("status" to it[0].toString(), "quantidade" to (it[1] as Number).toInt())
//    }
//
//fun getCandidatosPorVaga() =
//    repo.candidatosPorVaga().map {
//        mapOf("vaga" to it[0].toString(), "totalCandidatos" to (it[1] as Number).toInt())
//    }
//
//fun getTipoContrato() =
//    repo.tipoContrato().map {
//        mapOf("contrato" to it[0].toString(), "total" to (it[1] as Number).toInt())
//    }
//
//fun getTempoPreenchimento() =
//    repo.tempoPreenchimento().map {
//        mapOf("mes" to (it[0] as Number).toInt(), "dias" to (it[1] as Number).toInt())
//    }
//
//// --- NOVOS GRÁFICOS ---
//
//fun getCandidatesByState(): List<CandidatesByStateDTO> =
//    repo.countCandidatesByState().map { row ->
//        CandidatesByStateDTO(
//            state = row[0] as? String ?: "N/A",
//            total = (row[1] as? Number)?.toLong() ?: 0L
//        )
//    }
//
//fun getMatchLevelDistribution(): List<MatchLevelDistributionDTO> =
//    repo.countMatchLevelDistribution().map { row ->
//        MatchLevelDistributionDTO(
//            matchLevel = row[0] as? String ?: "N/A",
//            total = (row[1] as? Number)?.toLong() ?: 0L
//        )
//    }
//
//fun getTopCandidatesByVacancy(vacancyId: Int): List<TopCandidateMatchDTO> =
//    repo.findTopCandidatesByVacancy(vacancyId).map { row ->
//        TopCandidateMatchDTO(
//            matchId = (row[0] as Number).toInt(),
//            candidateId = (row[1] as Number).toInt(),
//            candidateName = row[2] as? String ?: "",
//            vacancyId = (row[3] as Number).toInt(),
//            vacancyPosition = row[4] as? String ?: "",
//            score = (row[5] as Number).toDouble(),
//            matchLevel = row[6] as? String ?: ""
//        )
//    }
//
//fun getCandidatesByStage(): List<StageCountDTO> =
//    repo.countCandidatesByStage().map { row ->
//        StageCountDTO(
//            stageName = row[0] as? String ?: "",
//            total = (row[1] as? Number)?.toLong() ?: 0L
//        )
//    }
//
//fun getAvgTimeByStage(): List<StageAvgTimeDTO> =
//    repo.avgTimeByStage().map { row ->
//        StageAvgTimeDTO(
//            stageName = row[0] as? String ?: "",
//            avgDays = (row[1] as? Number)?.toDouble() ?: 0.0
//        )
//    }
//
//fun getVacancyStatusSummary(): List<VacancyStatusSummaryDTO> =
//    repo.countVacanciesByStatus().map { row ->
//        VacancyStatusSummaryDTO(
//            status = row[0] as? String ?: "",
//            total = (row[1] as? Number)?.toLong() ?: 0L
//        )
//    }
//
//fun getManagerPerformance(): List<ManagerPerformanceDTO> =
//    repo.managerPerformance().map { row ->
//        ManagerPerformanceDTO(
//            managerId = (row[0] as Number).toInt(),
//            managerName = row[1] as? String ?: "",
//            totalVacancies = (row[2] as Number).toLong(),
//            approvedVacancies = (row[3] as Number).toLong(),
//            rejectedVacancies = (row[4] as Number).toLong(),
//            avgApprovalDays = (row[5] as? Number)?.toDouble()
//        )
//    }