package project.api.app.candidates.data

data class CandidateDetailsDTO(
    val idCandidate: Int,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val state: String,
    val education: String?,
    val skills: String?,
    val experience: String?,
    val profilePictureBase64: String? // converte o byte[] para base64
)
