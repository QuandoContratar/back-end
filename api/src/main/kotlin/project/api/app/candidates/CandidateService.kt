package project.api.app.candidates

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import project.api.app.Selection.SelectionProcessRepository
import project.api.app.Selection.data.CandidateCardDTO
import project.api.app.candidates.data.Candidate
import project.api.core.CrudService
import java.time.LocalDate
import java.time.Period

@Service
class CandidateService (
    val candidateRepository: CandidateRepository,
    val selectionRepository: SelectionProcessRepository
): CrudService<Candidate>(candidateRepository) {
    override fun delete(id: Int): Candidate {
        return super.delete(id)
    }

    fun saveResumeOnly(file: MultipartFile): Candidate {
        val candidate = Candidate(resume = file.bytes)
        return insert(candidate, null)
    }

    fun calculate(birthDate: LocalDate?): Int {
        return if (birthDate != null) {
            Period.between(birthDate, LocalDate.now()).years
        } else {
            0 // ou algum valor padrão, tipo -1, ou opcional
        }
    }

    fun listAllCandidates(): List<CandidateCardDTO> {
        return candidateRepository.findAll().map { candidate ->

            val currentProcess = selectionRepository
                .findTopByCandidateAndProgressLessThanOrderByCreatedAtDesc(candidate, 100.0)


            val vacancy = currentProcess?.vacancy


            CandidateCardDTO(
                idCandidate = candidate.idCandidate ?: 0,
                name = candidate.name ?: "",
                age = calculate(candidate.birth),
                location = candidate.state ?: "",
                vacancy = vacancy?.position_job ?: "sem vaga atual",
                area = vacancy?.area ?: "sem área",

            )


        }
    }






}