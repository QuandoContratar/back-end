package project.api.app.vacancies

import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.api.app.users.data.User
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancySummaryDTO
import project.api.core.CrudController

@RestController
@RequestMapping("/vacancies")
class VacancyController(
    val vacancyService: VacancyService
): CrudController<Vacancy>(vacancyService){
    @GetMapping("/activesVacancies")
    fun listVacancies(): List<VacancySummaryDTO> {
        return vacancyService.listVacancies()
    }

    @PostMapping("/send-massive")
    fun sendMassive(
        @RequestBody vacancies: List<Vacancy>,
        session: HttpSession
    ): ResponseEntity<String> {
        println(session.getAttribute("usuarioLogado"))
        val usuario = session.getAttribute("usuarioLogado")
        if (usuario !is User) {
            return ResponseEntity.status(401).body("Usuário não logado")
        }

        vacancyService.uploadAllVacancies(vacancies, usuario)
        return ResponseEntity.ok("Vagas enviadas para aprovação")

    }

}

