package project.api.app.match.parser

data class VacancyRequirementsParsed(
    val minExperienceYears: Int? = null,
    val requiredEducationLevel: String? = null,
    val preferredEducationArea: String? = null,
    val requiredSkills: List<String> = emptyList(),
    val niceToHaveSkills: List<String> = emptyList(),
    val requiredState: String? = null,
    val remoteAllowed: Boolean = true
)