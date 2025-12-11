package project.api.app.dashboard.old

data class ManagerPerformanceDTO(
    val managerId: Int,
    val managerName: String,
    val totalVacancies: Long,
    val approvedVacancies: Long,
    val rejectedVacancies: Long,
    val avgApprovalDays: Double?
)