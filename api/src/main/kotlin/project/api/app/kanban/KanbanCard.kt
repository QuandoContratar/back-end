package project.api.app.kanban

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import project.api.app.candidates.data.Candidate
import project.api.app.match.MatchLevel
import project.api.app.vacancies.data.Vacancy
import java.time.LocalDateTime

@Entity
@Table(name = "kanban_card")
data class KanbanCard(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_card: Int = 0,

    @ManyToOne
    @JoinColumn(name = "fk_candidate")
    val candidate: Candidate,

    @ManyToOne
    @JoinColumn(name = "fk_vacancy")
    val vacancy: Vacancy,

    @ManyToOne
    @JoinColumn(name = "fk_stage")
    val stage: KanbanStage,

    @Enumerated(EnumType.STRING)
    val match_level: MatchLevel = MatchLevel.MEDIO,

    val rejectionReason: String? = null,

    val created_at: LocalDateTime = LocalDateTime.now(),

    val updated_at: LocalDateTime = LocalDateTime.now()
)

