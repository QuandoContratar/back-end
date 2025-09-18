package project.api.user

//import org.modelmapper.ModelMapper
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase
import org.springframework.stereotype.Service
import project.api.core.CrudService
import project.api.core.errors.NotFoundException
import project.api.user.data.User
import project.api.user.data.UserLoginDto

@Service
class UserService(
    val userRepository: UserRepository,
//    val mapper: ModelMapper
): CrudService<User>(userRepository) {
    fun login(dto: UserLoginDto): User {
        val user = this.findAll(User(name = dto.name, password = dto.password), true)
        if (user.isEmpty()) {
            throw NotFoundException()
        }

        return user[0]
    }
}