package project.api.core.enums

enum class EntitiesEnum(val entityName: String, val entity: Class<*>? = null) {
    USER("Users");

    companion object {
        fun fromClasse(entitySearch:Class<*>):EntitiesEnum? {
            entries.forEach {
                if (it.entity == entitySearch) return it;
            }
            return null;
        }
    }
}