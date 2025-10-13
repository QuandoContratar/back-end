package project.api.app.vacancies.data

import jakarta.persistence.*
import project.api.app.candidates.data.Candidate
import project.api.app.users.data.User

@Entity
data class selectionProcess(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var progress: Double? = null,
    var currentStage: CurrentStage? = null,
    var outcome: Outcome? = null,
    @ManyToOne
    @JoinColumn(name = "fk_candidate")
    var candidate: Candidate? = null,
    @ManyToOne
    @JoinColumn(name = "fk_recruiter")
    var recruiter: User? = null
)
