package project.api.app.match


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.api.app.Selection.SelectionProcessRepository
import project.api.app.Selection.data.CurrentStage
import project.api.app.Selection.data.Outcome
import project.api.app.Selection.data.SelectionProcess
import project.api.app.candidates.CandidateRepository
import project.api.app.users.UserService
import project.api.app.vacancies.VacancyService
import java.time.LocalDateTime

@RestController
@RequestMapping("/match")
class CandidateMatchController(
    private val matchService: CandidateMatchService,
    private val candidateRepository: CandidateRepository,
    private val vacancyService: VacancyService,
    private val candidateMatchRepository: CandidateMatchRepository,
    private val selectionProcessRepository: SelectionProcessRepository,
    private val loggedUserProvider: UserService.LoggedUserProvider,
    private val smartMatchService: CandidateSmartMatchService
) {

    // =============================
    // 1. LISTAR TODOS OS MATCHES
    // =============================
    @GetMapping
    fun listAllMatches(): ResponseEntity<List<CandidateMatchDTO>> {
        val matches = candidateMatchRepository.findAll()
        val dtos = matches.map { CandidateMatchDTO.from(it) }
        return ResponseEntity.ok(dtos)
    }

    // =============================
    // 2. LISTAR MATCHES POR VAGA
    // =============================
    @GetMapping("/{vacancyId}/list")
    fun listMatchesByVacancy(@PathVariable vacancyId: Int): ResponseEntity<List<CandidateMatchDTO>> {

        val matches = candidateMatchRepository.findByVacancyId(vacancyId)
        val dtos = matches.map { CandidateMatchDTO.from(it) }

        return ResponseEntity.ok(dtos)
    }

    // =============================
    // 3. CALCULAR MATCH DA VAGA
    // =============================
    @PostMapping("/{vacancyId}")
    fun calculateMatch(@PathVariable vacancyId: Int): ResponseEntity<List<CandidateMatchDTO>> {
        val vacancy = vacancyService.findById(vacancyId)
        val candidates = candidateRepository.findAll()

        val results = candidates.map { candidate ->
            val score = smartMatchService.calculateMatch(candidate, vacancy)
            val matchLevel = matchService.getMatchLevel(score)

            val saved = candidateMatchRepository.save(
                CandidateMatch(
                    candidate = candidate,
                    vacancy = vacancy,
                    score = score,
                    matchLevel = matchLevel
                )
            )

            CandidateMatchDTO.from(saved)
        }

        return ResponseEntity.ok(results)
    }

    // =============================
    // 4. ACEITAR MATCH
    // =============================
    @PostMapping("/{matchId}/accept")
    fun acceptCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
        val match = candidateMatchRepository.findById(matchId).orElseThrow()

        val process = SelectionProcess(
            progress = 0.0,
            currentStage = CurrentStage.aguardando_triagem,
            outcome = Outcome.aprovado,
            createdAt = LocalDateTime.now(),
            candidate = match.candidate,
            vacancy = match.vacancy,
            recruiter = loggedUserProvider.getLoggedUser()
        )

        return ResponseEntity.ok(selectionProcessRepository.save(process))
    }

    // =============================
    // 5. REJEITAR MATCH
    // =============================
    @PostMapping("/{matchId}/reject")
    fun rejectCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
        val match = candidateMatchRepository.findById(matchId).orElseThrow()

        val process = SelectionProcess(
            progress = 0.0,
            currentStage = CurrentStage.aguardando_triagem,
            outcome = Outcome.reprovado,
            createdAt = LocalDateTime.now(),
            candidate = match.candidate,
            vacancy = match.vacancy,
            recruiter = loggedUserProvider.getLoggedUser()
        )

        return ResponseEntity.ok(selectionProcessRepository.save(process))
    }

    // =============================
    // 6. LISTAR MATCHES POR CANDIDATO
    // =============================
    @GetMapping("/candidate/{candidateId}")
    fun listMatchesByCandidate(@PathVariable candidateId: Int): ResponseEntity<List<CandidateMatchDTO>> {
        val matches = candidateMatchRepository.findByCandidate_idCandidate(candidateId)
        val dtos = matches.map { CandidateMatchDTO.from(it) }
        return ResponseEntity.ok(dtos)
    }
}




//@RestController
//@RequestMapping("/match")
//class CandidateMatchController(
//    private val matchService: CandidateMatchService,
//    private val candidateRepository: CandidateRepository,
//    private val vacancyService: VacancyService,
//    private val candidateMatchRepository: CandidateMatchRepository,
//    private val selectionProcessRepository: SelectionProcessRepository,
//    private val loggedUserProvider: UserService.LoggedUserProvider,
//    private val smartMatchService: CandidateSmartMatchService
//
//
//) {
//    /**
//     * Calcula os matches de todos candidatos para a vaga selecionada.
//     */
//    @PostMapping("/{vacancyId}")
//    fun calculateMatch(@PathVariable vacancyId: Int): ResponseEntity<List<CandidateMatchDTO>> {
//        val vacancy = vacancyService.findById(vacancyId)
//        val candidates = candidateRepository.findAll()
//
//        val results = mutableListOf<CandidateMatchDTO>()
//
//        candidates.forEach { candidate ->
//
//            // CALCULA O SMART MATCH (com parsing + pesos)
//            val score = smartMatchService.calculateMatch(candidate, vacancy)
//            val matchLevel = matchService.getMatchLevel(score)
//
//
//            // SALVA O NOVO MATCH
//            val saved = candidateMatchRepository.save(
//                CandidateMatch(
//                    candidate = candidate,
//                    vacancy = vacancy,
//                    score = score,
//                    matchLevel = matchLevel
//                )
//            )
//
//            // ADICIONA AO RETORNO
//            results.add(CandidateMatchDTO.from(candidate, vacancy, saved.score, saved.matchLevel))
//        }
//
//        return ResponseEntity.ok(results)
//    }
//
//    /**
//     * Aceita um candidato e cria o processo seletivo.
//     */
//    @PostMapping("/{matchId}/accept")
//    fun acceptCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
//        val match = candidateMatchRepository.findById(matchId).orElseThrow()
//
//        val process = SelectionProcess(
//            progress = 0.0,
//            currentStage = CurrentStage.aguardando_triagem,
//            outcome = Outcome.aprovado,
//            createdAt = LocalDateTime.now(),
//            candidate = match.candidate,
//            vacancy = match.vacancy,
//            recruiter = loggedUserProvider.getLoggedUser()
//        )
//
//        return ResponseEntity.ok(selectionProcessRepository.save(process))
//    }
//
//    /**
//     * Rejeita um candidato e cria o processo seletivo.
//     */
//    @PostMapping("/{matchId}/reject")
//    fun rejectCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
//        val match = candidateMatchRepository.findById(matchId).orElseThrow()
//
//        val process = SelectionProcess(
//            progress = 0.0,
//            currentStage = CurrentStage.triagem_inicial,
//            outcome = Outcome.reprovado,
//            createdAt = LocalDateTime.now(),
//            candidate = match.candidate,
//            vacancy = match.vacancy,
//            recruiter = loggedUserProvider.getLoggedUser()
//        )
//
//        return ResponseEntity.ok(selectionProcessRepository.save(process))
//    }
//
//    /**
//     * Lista matches por vaga com filtros opcionais.
//     */
//    @GetMapping("/{vacancyId}")
//    fun listMatches(
//        @PathVariable vacancyId: Int,
//        @RequestParam(required = false) level: String?,
//        @RequestParam(required = false) area: String?,
//        @RequestParam(required = false) state: String?
//    ): ResponseEntity<List<CandidateMatchDTO>> {
//
//        var matches = candidateMatchRepository.findByVacancyId(vacancyId)
//
//        if (!level.isNullOrEmpty()) {
//            matches = matches.filter { it.matchLevel.name.equals(level, ignoreCase = true) }
//        }
//
//        if (!area.isNullOrEmpty()) {
//            matches = matches.filter { it.vacancy.area.equals(area, ignoreCase = true) }
//        }
//
//        if (!state.isNullOrEmpty()) {
//            matches = matches.filter { it.candidate.state.equals(state, ignoreCase = true) }
//        }
//
//        val dtos = matches.map {
//            CandidateMatchDTO.from(it.candidate, it.vacancy, it.score, it.matchLevel)
//        }
//
//        return ResponseEntity.ok(dtos)
//    }
//
//    /**
//     * Lista matches por candidato.
//     */
//    @GetMapping("/candidate/{candidateId}")
//    fun listMatchesByCandidate(@PathVariable candidateId: Int): ResponseEntity<List<CandidateMatchDTO>> {
//        val matches = candidateMatchRepository.findByCandidate_idCandidate(candidateId)
//
//        val dtos = matches.map {
//            CandidateMatchDTO.from(it.candidate, it.vacancy, it.score, it.matchLevel)
//        }
//
//        return ResponseEntity.ok(dtos)
//    }
//}

//@RestController
//@RequestMapping("/match")
//class CandidateMatchController(
//    private val matchService: CandidateMatchService,
//    private val candidateRepository: CandidateRepository,
//    private val vacancyService: VacancyService,
//    private val candidateMatchRepository: CandidateMatchRepository,
//    private val selectionProcessRepository: SelectionProcessRepository,
//    private val loggedUserProvider: UserService.LoggedUserProvider
//) {
//
//    @PostMapping("/{vacancyId}")
//    fun calculateMatch(@PathVariable vacancyId: Int): ResponseEntity<List<CandidateMatchDTO>> {
//        val vacancy = vacancyService.findById(vacancyId)
//        val candidates = candidateRepository.findAll()
//
//        val matches = candidates.map { candidate ->
//            val score = matchService.calculateMatch(candidate, vacancy)
//            val matchLevel = matchService.getMatchLevel(score)
//
//            candidateMatchRepository.save(
//                CandidateMatch(
//                    candidate = candidate,
//                    vacancy = vacancy,
//                    score = score,
//                    matchLevel = matchLevel
//                )
//            )
//
//            CandidateMatchDTO.from(candidate, vacancy, score, matchLevel)
//        }
//
//        return ResponseEntity.ok(matches)
//    }
//
//    @PostMapping("/{matchId}/accept")
//    fun acceptCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
//        val match = candidateMatchRepository.findById(matchId).orElseThrow()
//        val process = SelectionProcess(
//            progress = 0.0,
//            currentStage = CurrentStage.triagem_inicial,
//            outcome = Outcome.aprovado,
//            createdAt = LocalDateTime.now(),
//            candidate = match.candidate,
//            vacancy = match.vacancy,
//            recruiter = loggedUserProvider.getLoggedUser()
//        )
//        return ResponseEntity.ok(selectionProcessRepository.save(process))
//    }
//
//    @PostMapping("/{matchId}/reject")
//    fun rejectCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
//        val match = candidateMatchRepository.findById(matchId).orElseThrow()
//        val process = SelectionProcess(
//            progress = 0.0,
//            currentStage = CurrentStage.triagem_inicial,
//            outcome = Outcome.reprovado,
//            createdAt = LocalDateTime.now(),
//            candidate = match.candidate,
//            vacancy = match.vacancy,
//            recruiter = loggedUserProvider.getLoggedUser()
//
//        )
//        return ResponseEntity.ok(selectionProcessRepository.save(process))
//    }
//
//    @GetMapping("/{vacancyId}")
//    fun listMatches(
//        @PathVariable vacancyId: Int,
//        @RequestParam(required = false) level: String?,
//        @RequestParam(required = false) area: String?,
//        @RequestParam(required = false) state: String?
//    ): ResponseEntity<List<CandidateMatchDTO>> {
//        var matches = candidateMatchRepository.findByVacancyId(vacancyId)
//
//        // aplica filtros
//        if (level != null) {
//            matches = matches.filter { it.matchLevel.name == level.uppercase() }
//        }
//        if (area != null) {
//            matches = matches.filter { it.vacancy.area.equals(area, ignoreCase = true) }
//        }
//        if (state != null) {
//            matches = matches.filter { it.candidate.state.equals(state, ignoreCase = true) }
//        }
//
//        val dtos = matches.map {
//            CandidateMatchDTO.from(it.candidate, it.vacancy, it.score, it.matchLevel)
//        }
//
//        return ResponseEntity.ok(dtos)
//    }
//
//    @GetMapping("/candidate/{candidateId}")
//    fun listMatchesByCandidate(@PathVariable candidateId: Int): ResponseEntity<List<CandidateMatchDTO>> {
//        val matches = candidateMatchRepository.findByCandidate_idCandidate(candidateId)
//        val dtos = matches.map {
//            CandidateMatchDTO.from(it.candidate, it.vacancy, it.score, it.matchLevel)
//        }
//        return ResponseEntity.ok(dtos)
//    }
//
//
//
//}
