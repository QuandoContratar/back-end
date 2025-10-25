package project.api.app.users

//import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import project.api.app.users.data.LevelAccess
import project.api.core.CrudService
import project.api.core.errors.NotFoundException
import project.api.app.users.data.User
import project.api.app.users.data.UserLoginDto

@Service
class UserService(
    val userRepository: UserRepository,
//    val mapper: ModelMapper
): CrudService<User>(userRepository) {
    fun login(dto: UserLoginDto): User {
        val user = this.findAll(User(email = dto.email, password = dto.password), true)
        if (user.isEmpty()) {
            throw NotFoundException()
        }

        return user[0]
    }

    fun findByAccess(level: LevelAccess): List<User> {
        return userRepository.findByLevelAccess(level)
    }

    @Component
    class LoggedUserProvider(
        private val userRepository: UserRepository
    ) {
        fun getLoggedUser(): User {
            // por enquanto retorna sempre o 1º admin
            return userRepository.findById(1).orElseThrow()
        }
    }



}