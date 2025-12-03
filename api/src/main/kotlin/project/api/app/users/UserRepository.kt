package project.api.app.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import project.api.app.users.data.LevelAccess
import project.api.app.users.data.User

@Repository
interface UserRepository:JpaRepository<User, Int> {




        @Query("SELECT u FROM User u WHERE u.levelAccess = 'ADMIN'")
        fun findAdmins(): List<User>

        @Query("SELECT u FROM User u WHERE u.levelAccess = 'HR'")
        fun findHr(): List<User>

        @Query("SELECT u FROM User u WHERE u.levelAccess = 'MANAGER'")
        fun findManagers(): List<User>
    }






