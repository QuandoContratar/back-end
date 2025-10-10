package project.api.app.users.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Size

@Entity
data class Candidate(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCandidate: Int? = null,
    var name: String? = null,
    @field:Size(min = 13, max = 13)
    var phoneNumber: String? = null,
    var email: String? = null,
    @field:Size(min = 2, max = 2)
    var state: String? = null,
    var profilePicture: ByteArray? = null,
    var education: String? = null,
    var skills: String? = null,
    var resume: ByteArray? = null
)
