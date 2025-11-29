package project.api.app.vacancies

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpSession
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import project.api.app.users.data.User
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancyOpeningDTO
import project.api.app.vacancies.data.VacancySummaryDTO
import project.api.core.CrudController
import com.fasterxml.jackson.core.type.TypeReference

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

//    Abertura de vagas
    @PostMapping("/send-massive", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun sendMassive(
    @RequestPart("vacancies") vacancies: String,
    @RequestPart("files") files: List<MultipartFile>,
    session: HttpSession
    ): ResponseEntity<String> {
        println(session.getAttribute("usuarioLogado"))
        val usuario = session.getAttribute("usuarioLogado")
        if (usuario !is User) {
            return ResponseEntity.status(401).body("Usuário não logado")
        }

    println(vacancies)
    println("teste")
    println(files)


    val dto = objectMapper.readValue(vacancies, object : com.fasterxml.jackson.core.type.TypeReference<List<VacancyOpeningDTO>>(){})
    val filesOpening: List<ByteArray> = files.map {it.bytes}
    val vacanciesIds = vacancyService.uploadAllVacanciesPart1(dto, usuario)
    vacancyService.uploadAllVacanciesPart2(filesOpening, vacanciesIds)
        return ResponseEntity.ok("Vagas enviadas para aprovação")


    }

}

