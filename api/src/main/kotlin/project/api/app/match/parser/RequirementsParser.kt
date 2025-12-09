package project.api.app.match.parser

object RequirementsParser {

    // Lista conhecida de skills pra reconhecer automaticamente
    private val knownSkills = listOf(
        "java","spring","spring boot","sql","mysql","postgresql","python","django","react",
        "node","aws","docker","kubernetes","git","figma","power bi","ml","machine learning"
    )

    fun parse(text: String): VacancyRequirementsParsed {
        val cleaned = text.lowercase()

        return VacancyRequirementsParsed(
            minExperienceYears = extractExperience(cleaned),
            requiredEducationLevel = extractEducationLevel(cleaned),
            preferredEducationArea = extractEducationArea(cleaned),
            requiredSkills = extractRequiredSkills(cleaned),
            niceToHaveSkills = extractNiceToHave(cleaned),
            requiredState = extractState(cleaned),
            remoteAllowed = detectRemote(cleaned)
        )
    }

    private fun extractExperience(text: String): Int? {
        val regex = Regex("(\\d+)\\+?\\s*(anos|ano) de experiência")
        return regex.find(text)?.groups?.get(1)?.value?.toIntOrNull()
    }

    private fun extractEducationLevel(text: String): String? =
        when {
            "superior" in text -> "superior"
            "ensino médio" in text || "medio" in text -> "medio"
            "técnico" in text -> "tecnico"
            "pós-graduação" in text || "pos" in text -> "pos"
            "mestrado" in text -> "mestrado"
            "doutorado" in text -> "doutorado"
            else -> null
        }

    private fun extractEducationArea(text: String): String? {
        val areas = listOf(
            "ciência da computação","sistemas de informação","engenharia de software","ads",
            "design","ux","dados","estatística"
        )
        return areas.firstOrNull { it in text }
    }

    private fun extractRequiredSkills(text: String): List<String> =
        knownSkills.filter { text.contains(it) }

    private fun extractNiceToHave(text: String): List<String> {
        // procuro textos tipo "desejável", "nice to have", "seria um diferencial"
        val niceBlockRegex = Regex("(desejável:|nice to have:|diferencial:)([\\s\\S]*)")
        val block = niceBlockRegex.find(text)?.groups?.get(2)?.value ?: return emptyList()

        return knownSkills.filter { block.contains(it) }
    }

    private fun extractState(text: String): String? {
        val estados = listOf("sp","rj","mg","sc","pr","rs","ba","df","go","mt","ms")
        return estados.firstOrNull { " $it" in text }
    }

    private fun detectRemote(text: String): Boolean =
        text.contains("remoto") || text.contains("home office")
}
