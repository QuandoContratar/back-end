package project.api.app.candidates

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import project.api.app.Selection.data.CandidateCardDTO
import project.api.app.candidates.data.Candidate
import project.api.app.candidates.data.CandidateDetailsDTO
import project.api.app.candidates.data.CandidateProfile
import project.api.app.candidates.data.SeniorityLevel
import project.api.app.candidates.dto.CandidateProfileDTO
import project.api.app.candidates.dto.RejectCandidateRequest
import project.api.core.CrudController
import project.api.core.utils.FileMediaTypeResolver
import project.api.core.utils.FileStorageService
import java.time.LocalDateTime



@RestController
@RequestMapping("/candidates")

class CandidateController(
    private val candidateService: CandidateService,
    private val CandidateProfileRepository: CandidateProfileRepository
) : CrudController<Candidate>(candidateService) {



//    @GetMapping("{id}/resume")
//    fun downloadResume(@PathVariable id: Int): ResponseEntity<ByteArray> {
//        val candidate = candidateService.findById(id)
//
//        val fileMeta = candidate.resumeFile ?: return ResponseEntity.notFound().build()
//
//        val fileBytes = FileStorageService.loadFile(fileMeta.fileKey)
//
//        val headers = HttpHeaders().apply {
//            contentType = MediaType.parseMediaType(FileMediaTypeResolver.resolve(fileMeta.fileType))
//            contentDisposition = ContentDisposition.builder("attachment")
//                .filename(fileMeta.fileName)
//                .build()
//        }
//
//        return ResponseEntity.ok()
//            .headers(headers)
//            .body(fileBytes)
//    }


    @GetMapping("{id}/resume")
    fun downloadResume(@PathVariable id: Int): ResponseEntity<ByteArray>{
        val candidate = candidateService.findById(id)

        val resume = candidate.resume ?: return ResponseEntity.notFound().build()

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_PDF
            contentDisposition = ContentDisposition.builder("attachment")
                .filename("resume_${id}.pdf")
                .build()
        }

        return ResponseEntity.ok()
            .headers(headers)
            .body(resume)
    }

    @PostMapping("/upload-multiple-resumes")
    fun uploadMultipleResumes(@RequestParam("files") files: Array<MultipartFile>): ResponseEntity<List<Candidate>>{
        val candidates = files.map {
                file ->
            candidateService.saveResumeOnly(file)
        }
        return ResponseEntity.ok(candidates)
    }

    @GetMapping("/candidatesList")
    fun listCandidates(): ResponseEntity<List<CandidateCardDTO>>{
        val candidates = candidateService.listAllCandidates()
        return ResponseEntity.ok(candidates)

    }

    @GetMapping("{id}/details")
    fun getCandidateDetails(@PathVariable id: Int): ResponseEntity<CandidateDetailsDTO> {
        val details = candidateService.getCandidateDetails(id)
        return ResponseEntity.ok(details)
    }

    // Busca a experiência de um candidato
    @GetMapping("/{id}/experience")
    fun getCandidateExperience(@PathVariable id: Int): ResponseEntity<String> {
        val candidate = candidateService.findById(id)
        val experience = candidate.experience ?: "Experiência não informada"
        return ResponseEntity.ok(experience)
    }

    // Atualiza a experiência de um candidato
    @PutMapping("/{id}/experience")
    fun updateExperience(
        @PathVariable id: Int,
        @RequestBody request: Map<String, String>
    ): ResponseEntity<Candidate> {
        val candidate = candidateService.findById(id)
        val updated = candidate.copy(
            experience = request["experience"] // pega só o campo experience
        )
        val saved = candidateService.repository.save(updated)
        return ResponseEntity.ok(saved)
    }

    //Deleta candidato
    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Int): ResponseEntity<Candidate?>{
        val deletedCandidate = candidateService.deleteCandidate(id)
        return ResponseEntity.ok(deletedCandidate)
    }


    /**
     * Lista candidatos por etapa do processo seletivo
     * GET /candidates/stage/{stage}
     */
    @GetMapping("/stage/{stage}")
    fun findByStage(@PathVariable stage: String): ResponseEntity<List<CandidateCardDTO>> {
        val candidates = candidateService.findByStage(stage)
        return ResponseEntity.ok(candidates)
    }

    /**
     * Avança candidato para próxima etapa
     * POST /candidates/{id}/advance
     */
    @PostMapping("/{id}/advance")
    fun advanceStage(@PathVariable id: Long): ResponseEntity<CandidateCardDTO> {
        val candidate = candidateService.advanceStage(id)
        return ResponseEntity.ok(candidate)
    }

    /**
     * Aprova candidato (contratação)
     * POST /candidates/{id}/approve
     */
    @PostMapping("/{id}/approve")
    fun approve(@PathVariable id: Long): ResponseEntity<CandidateCardDTO> {
        val candidate = candidateService.approve(id)
        return ResponseEntity.ok(candidate)
    }

    /**
     * Reprova candidato
     * POST /candidates/{id}/reject
     */
    @PostMapping("/{id}/reject")
    fun reject(
        @PathVariable id: Long,
        @RequestBody(required = false) body: RejectCandidateRequest?
    ): ResponseEntity<CandidateCardDTO> {
        val candidate = candidateService.reject(id, body?.reason)
        return ResponseEntity.ok(candidate)
    }

    /**
     * Lista candidatos por vaga
     * GET /candidates/vacancy/{vacancyId}
     */
    @GetMapping("/vacancy/{vacancyId}")
    fun findByVacancy(@PathVariable vacancyId: Long): ResponseEntity<List<CandidateCardDTO>> {
        val candidates = candidateService.findByVacancy(vacancyId)
        return ResponseEntity.ok(candidates)
    }

    // ============================================================
    //  NOVO ENDPOINT: IA envia o perfil estruturado do candidato
    //  POST /candidates/{id}/profile
    // ============================================================

    @PostMapping("/{id}/profile")
    fun saveCandidateProfile(
        @PathVariable id: Int,
        @RequestBody dto: CandidateProfileDTO
    ): ResponseEntity<CandidateProfile> {

        val candidate = candidateService.findById(id)

        val objectMapper = jacksonObjectMapper()
        val rawJson = objectMapper.writeValueAsString(dto)

        // SKILLS
        val hardSkills = dto.skills.joinToString(",") { it.trim() }
        val softSkills = dto.softSkills?.joinToString(",") { it.trim() }

        // ROLE PRINCIPAL
        val mainRole = dto.experiences.firstOrNull()?.role

        // STACK PRINCIPAL (tecnologia mais repetida)
        val mainStack = dto.experiences
            .flatMap { it.technologies ?: emptyList() }
            .groupingBy { it.lowercase() }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key

        // SENIORIDADE
        val seniorityEnum = dto.seniority?.uppercase()?.let { str ->
            when {
                "JUNIOR" in str -> SeniorityLevel.JUNIOR
                "PLENO" in str -> SeniorityLevel.PLENO
                "SENIOR" in str -> SeniorityLevel.SENIOR
                "LEAD" in str -> SeniorityLevel.LEAD
                else -> null
            }
        }

        val location = dto.location

        val existing = CandidateProfileRepository.findByCandidate(candidate)

        val profile = if (existing != null) {
            existing.apply {
                this.rawJson = rawJson
                this.totalExperienceYears = dto.totalExperienceYears
                this.mainSeniority = seniorityEnum
                this.mainStack = mainStack
                this.mainRole = mainRole
                this.city = location?.city
                this.state = location?.state
                this.remotePreference = location?.workFormat?.uppercase()
                this.hardSkills = hardSkills
                this.softSkills = softSkills
                this.updatedAt = LocalDateTime.now()
            }
        } else {
            CandidateProfile(
                candidate = candidate,
                rawJson = rawJson,
                totalExperienceYears = dto.totalExperienceYears,
                mainSeniority = seniorityEnum,
                mainStack = mainStack,
                mainRole = mainRole,
                city = location?.city,
                state = location?.state,
                remotePreference = location?.workFormat?.uppercase(),
                hardSkills = hardSkills,
                softSkills = softSkills
            )
        }

        val saved = CandidateProfileRepository.save(profile)

        // -------- ATUALIZA CAMPOS DO CANDIDATE (para compatibilidade) --------
        candidate.name = dto.name ?: candidate.name
        candidate.email = dto.email ?: candidate.email
        candidate.phoneNumber = dto.phone ?: candidate.phoneNumber

        candidate.education = dto.education.joinToString(" | ") {
            listOfNotNull(it.level, it.course, it.institution).joinToString(" - ")
        }

        candidate.skills = hardSkills
        candidate.experience = dto.experiences.joinToString(" | ") {
            listOfNotNull(it.role, it.company).joinToString(" em ")
        }

        candidate.state = location?.state ?: candidate.state

        candidateService.repository.save(candidate)

        return ResponseEntity.ok(saved)
    }


}