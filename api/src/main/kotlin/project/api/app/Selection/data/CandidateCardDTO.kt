package project.api.app.Selection.data

import java.time.LocalDateTime

data class CandidateCardDTO(
    val idCandidate: Int,
    val name: String,
    val age: Int,
    val location: String,
    val vacancy: String,
    val area: String,
//    val createdAt: LocalDateTime
)
