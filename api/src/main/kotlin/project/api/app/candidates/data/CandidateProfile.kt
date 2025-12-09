package project.api.app.candidates.data

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "candidate_profile")
data class CandidateProfile(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProfile: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_candidate", nullable = false)
    var candidate: Candidate,

    @Column(name = "raw_json", columnDefinition = "json", nullable = false)
    var rawJson: String,

    @Column(name = "total_experience_years")
    var totalExperienceYears: Double? = null,

    @Column(name = "main_seniority")
    @Enumerated(EnumType.STRING)
    var mainSeniority: SeniorityLevel? = null,

    @Column(name = "main_stack")
    var mainStack: String? = null,

    @Column(name = "main_role")
    var mainRole: String? = null,

    @Column(name = "city")
    var city: String? = null,

    @Column(name = "state")
    var state: String? = null,

    @Column(name = "remote_preference")
    var remotePreference: String? = null, // "REMOTO", "HIBRIDO", "PRESENCIAL"

    @Column(name = "hard_skills")
    var hardSkills: String? = null, // separado por vírgula

    @Column(name = "soft_skills")
    var softSkills: String? = null, // separado por vírgula

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now()
)

enum class SeniorityLevel {
    JUNIOR, PLENO, SENIOR, LEAD
}
