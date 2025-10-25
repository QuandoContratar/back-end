package project.api.app.candidates.data

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import project.api.app.File.data.FileMetadata
import java.time.LocalDate

@Entity
data class Candidate(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCandidate: Int? = null,
    var name: String? = null,
    var birth: LocalDate? = null,
    @field:Size(min = 14, max = 14)
    var phoneNumber: String? = null,
    var email: String? = null,
    @field:Size(min = 2, max = 2)
    var state: String? = null,
    var profilePicture: ByteArray? = null,
    var education: String? = null,
    var skills: String? = null,

//    @ManyToOne
//    @JoinColumn(name = "resume_file_id")
//    var resumeFile: FileMetadata? = null
    var resume: ByteArray? = null,
)
