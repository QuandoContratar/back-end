package project.api.app.match

import jakarta.persistence.*
import project.api.app.candidates.data.Candidate
import project.api.app.vacancies.data.Vacancy

@Entity
@Table(name = "candidate_match")
data class CandidateMatch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idMatch: Int? = null,

    @ManyToOne
    @JoinColumn(name = "fk_candidate", nullable = false)
    val candidate: Candidate,

    @ManyToOne
    @JoinColumn(name = "fk_vacancy", nullable = false)
    val vacancy: Vacancy,

    @Column(nullable = false)
    val score: Double,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val matchLevel: MatchLevel
)

enum class MatchLevel {
    BAIXO,
    MEDIO,
    ALTO,
    DESTAQUE
}
