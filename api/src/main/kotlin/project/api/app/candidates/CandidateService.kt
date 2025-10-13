package project.api.app.candidates

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import project.api.app.candidates.data.Candidate
import project.api.core.CrudService

@Service
class CandidateService (
    val candidateRepository: CandidateRepository
): CrudService<Candidate>(candidateRepository) {
    override fun delete(id: Int): Candidate {
        return super.delete(id)
    }

    fun saveResumeOnly(file: MultipartFile): Candidate {
        val candidate = Candidate(resume = file.bytes)
        return insert(candidate, null)
    }





}