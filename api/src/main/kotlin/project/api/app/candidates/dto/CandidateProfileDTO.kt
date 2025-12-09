package project.api.app.candidates.dto


data class CandidateProfileDTO(
    val name: String?,
    val email: String?,
    val phone: String?,
    val location: LocationDTO?,
    val education: List<EducationDTO>,
    val experiences: List<ExperienceDTO>,
    val skills: List<String>,
    val softSkills: List<String>?,
    val totalExperienceYears: Double?,
    val seniority: String?
)


data class EducationDTO(
    val institution: String?,
    val course: String?,
    val level: String?,       // "medio","tecnico","superior","pos","mestrado","doutorado"
    val startYear: Int?,
    val endYear: Int?
)

data class ExperienceDTO(
    val company: String?,
    val role: String?,
    val startDate: String?,   // "2020-06"
    val endDate: String?,     // "2022-12" ou null se atual
    val technologies: List<String>?,
    val responsibilities: List<String>?
)

data class LocationDTO(
    val city: String?,
    val state: String?,
    val workFormat: String?   // "remoto","hibrido","presencial"
)
