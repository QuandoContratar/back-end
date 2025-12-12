//package project.api.app.match
//
//import jakarta.persistence.*
//import project.api.app.candidates.data.Candidate
//import project.api.app.vacancies.data.Vacancy
//
//@Entity
//@Table(name = "candidate_match")
//data class CandidateMatch(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Int? = null,
//
//    @ManyToOne
//    @JoinColumn(name = "candidate_id", nullable = false)
//    val candidate: Candidate,
//
//    @ManyToOne
//    @JoinColumn(name = "vacancy_id", nullable = false)
//    val vacancy: Vacancy,
//
//    @Column(nullable = false)
//    val score: Double,
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    val matchLevel: MatchLevel
//)
//
//enum class MatchLevel {
//    BAIXO,
//    MEDIO,
//    ALTO,
//    DESTAQUE
//}
package project.api.app.match

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.springframework.beans.factory.config.YamlProcessor
import project.api.app.candidates.data.Candidate
import project.api.app.vacancies.data.Vacancy
import java.time.LocalDateTime

@Entity
@Table(name = "candidate_match")
data class CandidateMatch(

    @Id
    @Column(name = "id_match")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "fk_candidate", nullable = false)
    val candidate: Candidate,

    @ManyToOne
    @JoinColumn(name = "fk_vacancy", nullable = false)
    val vacancy: Vacancy,

    @Column(nullable = false)
    var score: Double,

    @Enumerated(EnumType.STRING)
    @Column(name = "match_level", nullable = false)
    var matchLevel: MatchLevel,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: MatchStatus = MatchStatus.PENDING


)

enum class MatchLevel {
    BAIXO,
    MEDIO,
    ALTO,
    DESTAQUE
}
enum class MatchStatus {
    PENDING,
    ACCEPTED,
    REJECTED
}
