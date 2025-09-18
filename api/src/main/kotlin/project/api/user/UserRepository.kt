package project.api.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import project.api.user.data.User

@Repository
interface UserRepository:JpaRepository<User, Int>