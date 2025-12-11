package project.api.app.dashboard.old

data class DashboardMetricsDTO(
    val totalVagas: Long,
    val totalCandidatos: Long,
    val vagasAbertas: Long,
    val taxaConversao: Double
)