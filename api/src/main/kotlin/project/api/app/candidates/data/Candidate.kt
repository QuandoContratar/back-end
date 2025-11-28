package project.api.app.candidates.data

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import project.api.app.File.data.FileMetadata
import java.time.LocalDate

@Entity
@Table(name = "candidate")
data class Candidate(
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidate")
    var idCandidate: Int? = null,
    @Column(name = "name", nullable = true)
    var name: String? = null,
    @Column(name = "birth", nullable = true)
    var birth: LocalDate? = null,
    @field:Size(min = 14, max = 14)
    @Column(name = "phone_number", nullable = true)
    var phoneNumber: String? = null,
    @Column(name = "email", nullable = true)
    var email: String? = null,
    @field:Size(min = 2, max = 2)
    @Column(name = "state", nullable = true)
    var state: String? = null,
    @Lob
    @Column(name = "profile_picture", columnDefinition = "LONGBLOB", nullable = true)
    var profilePicture: ByteArray? = null,
    @Column(name = "education", nullable = true)
    var education: String? = null,
    @Column(name = "skills", nullable = true)
    var skills: String? = null,
    @Column(name = "experience", nullable = true, columnDefinition = "TEXT")
    var experience: String? = null, // <-- NOVO CAMPO
//    @ManyToOne
//    @JoinColumn(name = "resume_file_id")
//    var resumeFile: FileMetadata? = null
    @Lob
    @Column(name = "resume", columnDefinition = "LONGBLOB", nullable = true)
    var resume: ByteArray? = null,
)
