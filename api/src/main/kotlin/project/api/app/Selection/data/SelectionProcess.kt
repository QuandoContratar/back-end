package project.api.app.Selection.data

import jakarta.persistence.*
import project.api.app.candidates.data.Candidate
import project.api.app.users.data.User
import project.api.app.vacancies.data.Vacancy
import java.time.LocalDateTime

@Entity
data class SelectionProcess(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSelection: Int? = null,
    var progress: Double,
    @Enumerated(EnumType.STRING)
    var currentStage: CurrentStage,
    @Enumerated(EnumType.STRING)
    var outcome: Outcome,
    var createdAt: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "fk_candidate")
    var candidate: Candidate? = null,
    @ManyToOne
    @JoinColumn(name = "fk_recruiter")
    var recruiter: User? = null,
    @ManyToOne
    @JoinColumn(name = "fk_vacancy")
    var vacancy: Vacancy? = null
)

