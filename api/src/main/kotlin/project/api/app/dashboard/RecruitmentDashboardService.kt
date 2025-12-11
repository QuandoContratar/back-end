package project.api.app.dashboard


import org.springframework.stereotype.Service

@Service
class RecruitmentDashboardService(
    private val repo: RecruitmentDashboardRepository
) {

    fun getVagasPorArea(area: String): List<AreaVacancyDTO> =
        repo.vagasPorArea(area).map { row ->
            AreaVacancyDTO(
                id = (row[0] as Number).toInt(),
                titulo = row[1].toString(),
                totalCandidatos = (row[2] as Number).toInt()
            )
        }

    fun getKPIs(vagaId: Int): VacancyKPIsDTO {
        val r = repo.kpis(vagaId)[0]

        return VacancyKPIsDTO(
            naoIniciado = (r[0] as Number).toInt(),
            avaliacaoTecnica = (r[1] as Number).toInt(),
            fitCultural = (r[2] as Number).toInt(),
            proposta = (r[3] as Number).toInt()
        )
    }

    fun getCandidatosDaVaga(vagaId: Int, match: String): List<VacancyCandidateDTO> =
        repo.candidatosDaVaga(vagaId, match).map { row ->
            VacancyCandidateDTO(
                id = (row[0] as Number).toInt(),
                nome = row[1].toString(),
                contrato = row[2]?.toString(),
                periodo = row[3]?.toString(),
                modelo = row[4]?.toString(),
                localidade = row[5]?.toString(),
                gestor = row[6]?.toString(),
                matching = (row[7] as Number).toDouble()
            )
        }

    fun getRetencao(vagaId: Int): VacancyRetentionDTO {
        val r = repo.retencao(vagaId)[0]

        return VacancyRetentionDTO(
            emAndamento = (r[0] as Number).toInt(),
            rejeitados = (r[1] as Number).toInt(),
            aprovados = (r[2] as Number).toInt()
        )
    }

    fun getOcupacao(vagaId: Int): VacancyOcupacaoDTO {
        val r = repo.ocupacao(vagaId)[0]

        val total = (r[0] as Number).toInt()
        val preenchidas = (r[1] as Number).toInt()

        return VacancyOcupacaoDTO(
            total = total,
            preenchidas = preenchidas,
            faltam = total - preenchidas
        )
    }
}
