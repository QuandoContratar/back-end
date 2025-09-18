package project.api.app.users.data

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Email

@Entity
@Table(name = "users")
data class User(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var name: String? = null,

    @field:Email
    var email: String? = null,

    var password: String? = null,

    var area: String? = null,

    @Enumerated(EnumType.STRING)
    var levelAccess: LevelAccess? = null,
)