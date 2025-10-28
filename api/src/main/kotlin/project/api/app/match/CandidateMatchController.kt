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
    private val loggedUserProvider: UserService.LoggedUserProvider
) {

    @PostMapping("/{vacancyId}")
    fun calculateMatch(@PathVariable vacancyId: Int): ResponseEntity<List<CandidateMatchDTO>> {
        val vacancy = vacancyService.findById(vacancyId)
        val candidates = candidateRepository.findAll()

        val matches = candidates.map { candidate ->
            val score = matchService.calculateMatch(candidate, vacancy)
            val matchLevel = matchService.getMatchLevel(score)

            candidateMatchRepository.save(
                CandidateMatch(
                    candidate = candidate,
                    vacancy = vacancy,
                    score = score,
                    matchLevel = matchLevel
                )
            )

            CandidateMatchDTO.from(candidate, vacancy, score, matchLevel)
        }

        return ResponseEntity.ok(matches)
    }

    @PostMapping("/match/{matchId}/accept")
    fun acceptCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
        val match = candidateMatchRepository.findById(matchId).orElseThrow()
        val process = SelectionProcess(
            progress = 0.0,
            currentStage = CurrentStage.triagem_inicial,
            outcome = Outcome.aprovado,
            createdAt = LocalDateTime.now(),
            candidate = match.candidate,
            vacancy = match.vacancy,
            recruiter = loggedUserProvider.getLoggedUser()
        )
        return ResponseEntity.ok(selectionProcessRepository.save(process))
    }

    @PostMapping("/match/{matchId}/reject")
    fun rejectCandidate(@PathVariable matchId: Int): ResponseEntity<SelectionProcess> {
        val match = candidateMatchRepository.findById(matchId).orElseThrow()
        val process = SelectionProcess(
            progress = 0.0,
            currentStage = CurrentStage.triagem_inicial,
            outcome = Outcome.reprovado,
            createdAt = LocalDateTime.now(),
            candidate = match.candidate,
            vacancy = match.vacancy,
            recruiter = loggedUserProvider.getLoggedUser()

        )
        return ResponseEntity.ok(selectionProcessRepository.save(process))
    }

    @GetMapping("/match/{vacancyId}")
    fun listMatches(
        @PathVariable vacancyId: Int,
        @RequestParam(required = false) level: String?,
        @RequestParam(required = false) area: String?,
        @RequestParam(required = false) state: String?
    ): ResponseEntity<List<CandidateMatchDTO>> {
        var matches = candidateMatchRepository.findByVacancyId(vacancyId)

        // aplica filtros
        if (level != null) {
            matches = matches.filter { it.matchLevel.name == level.uppercase() }
        }
        if (area != null) {
            matches = matches.filter { it.vacancy.area.equals(area, ignoreCase = true) }
        }
        if (state != null) {
            matches = matches.filter { it.candidate.state.equals(state, ignoreCase = true) }
        }

        val dtos = matches.map {
            CandidateMatchDTO.from(it.candidate, it.vacancy, it.score, it.matchLevel)
        }

        return ResponseEntity.ok(dtos)
    }

    @GetMapping("/candidate/{candidateId}")
    fun listMatchesByCandidate(@PathVariable candidateId: Int): ResponseEntity<List<CandidateMatchDTO>> {
        val matches = candidateMatchRepository.findByCandidate_idCandidate(candidateId)
        val dtos = matches.map {
            CandidateMatchDTO.from(it.candidate, it.vacancy, it.score, it.matchLevel)
        }
        return ResponseEntity.ok(dtos)
    }



}
