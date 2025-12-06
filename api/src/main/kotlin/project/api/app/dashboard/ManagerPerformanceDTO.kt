package project.api.app.dashboard

data class ManagerPerformanceDTO(
    val managerId: Int,
    val managerName: String,
    val totalVacancies: Long,
    val approvedVacancies: Long,
    val rejectedVacancies: Long,
    val avgApprovalDays: Double?
)