package project.api.user.data

import com.fasterxml.jackson.annotation.JsonIgnore

data class UserLoginDto(
    var name: String? = null,

    var password: String? = null,
)
