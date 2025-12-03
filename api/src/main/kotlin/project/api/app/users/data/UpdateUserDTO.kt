package project.api.app.users.data

data class UpdateUserDTO(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val area: String? = null,
)
