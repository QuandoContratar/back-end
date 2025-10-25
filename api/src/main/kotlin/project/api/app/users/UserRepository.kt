package project.api.app.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import project.api.app.users.data.LevelAccess
import project.api.app.users.data.User

@Repository
interface UserRepository:JpaRepository<User, Int> {
    fun findByLevelAccess(levelAccess: LevelAccess): List<User>
}

