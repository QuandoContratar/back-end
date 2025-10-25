package project.api.app.users

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.api.app.users.data.LevelAccess
import project.api.core.CrudController
import project.api.app.users.data.User
import project.api.app.users.data.UserLoginDto

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
): CrudController<User>(userService) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid dto: UserLoginDto): ResponseEntity<User> {
        val response = userService.login(dto)

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/by-access/{level}")
    fun findByAccess(@PathVariable level: LevelAccess): ResponseEntity<List<User>> {
        val users = userService.findByAccess(level)
        return ResponseEntity.ok(users)
    }

}