package project.api.app.vacancies

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpSession
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.api.app.users.data.User
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancyOpeningDTO
import project.api.app.vacancies.data.VacancySummaryDTO
import project.api.core.CrudController
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.readValue
import project.api.app.vacancies.data.SendToApprovalRequest
import project.api.app.vacancies.data.UpdateStatusRequest
import project.api.app.vacancies.data.VacancyDto

@RestController
@RequestMapping("/vacancies")
class VacancyController(
    private val objectMapper: ObjectMapper,
    val vacancyService: VacancyService
): CrudController<Vacancy>(vacancyService){
    @GetMapping("/activesVacancies")
    fun listVacancies(): List<VacancySummaryDTO> {
        return vacancyService.listVacancies()
    }

    @GetMapping("/pendingVacancies")
        fun listPendingVacancies(): List<VacancyOpeningDTO>{
            return vacancyService.listApprovalVacancies()
    }

    @PatchMapping("/updateStatus/{id}")
    fun approveVacancy(@PathVariable id: Int): ResponseEntity<String> {
        return vacancyService.openVacancy(id)
    }

//    Abertura de vagas
@PostMapping("/send-massive", consumes = [MediaType.APPLICATION_JSON_VALUE])
fun sendMassive(
    @RequestBody vacancies: List<VacancyOpeningDTO>,
    session: HttpSession
): ResponseEntity<String> {
    val usuario = session.getAttribute("usuarioLogado") as? User
        ?: return ResponseEntity.status(401).body("Usuário não logado")

    vacancyService.uploadAllVacancies(vacancies, usuario)

    return ResponseEntity.ok("Vagas enviadas para aprovação")
}


    /**
     * Lista vagas por gestor
     * GET /vacancies/manager/{managerId}
     */
    @GetMapping("/manager/{managerId}")
    fun findByManager(@PathVariable managerId: Long): ResponseEntity<List<VacancyDto>> {
        val vacancies = vacancyService.findByManager(managerId)
        return ResponseEntity.ok(vacancies)
    }

    /**
     * Lista vagas por status
     * GET /vacancies/status/{status}
     */
    @GetMapping("/status/{status}")
    fun findByStatus(@PathVariable status: String): ResponseEntity<List<VacancyDto>> {
        val vacancies = vacancyService.findByStatus(status)
        return ResponseEntity.ok(vacancies)
    }

    /**
     * Envia múltiplas vagas para aprovação
     * POST /vacancies/send-to-approval
     */
    @PostMapping("/send-to-approval")
    fun sendToApproval(@RequestBody request: SendToApprovalRequest): ResponseEntity<List<VacancyDto>> {
        val vacancies = vacancyService.sendToApproval(request.vacancyIds)
        return ResponseEntity.ok(vacancies)
    }
    /**
     * Atualiza status de uma vaga
     * PATCH /vacancies/{id}/status
     */
    @PatchMapping("/{id}/status")
    fun updateStatus(
        @PathVariable id: Long,
        @RequestBody request: UpdateStatusRequest
    ): ResponseEntity<VacancyDto> {
        val vacancy = vacancyService.updateStatus(id, request.statusVacancy)
        return ResponseEntity.ok(vacancy)
    }
}

