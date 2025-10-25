package project.api.app.candidates

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import project.api.app.File.FileMetadataRepository
import project.api.app.File.data.FileMetadata
import project.api.app.Selection.SelectionProcessRepository
import project.api.app.Selection.data.CandidateCardDTO
import project.api.app.candidates.data.Candidate
import project.api.app.candidates.data.CandidateDetailsDTO
import project.api.core.CrudService
import project.api.core.utils.FileStorageService
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

//    fun saveResumeOnly(file: MultipartFile): Candidate {
//        // 1- Criar metadata do arquivo
//        val metadata = FileMetadata(
//            fileName = file.originalFilename ?: "resume.pdf",
//            fileType = file.originalFilename?.substringAfterLast('.', "pdf"),
//            bucket = "local", // ou "S3", dependendo de onde for salvar
//            fileKey = "uploads/candidates/${file.originalFilename}"
//        )
//
//        // 2- Salvar o arquivo fisicamente
//        FileStorageService.saveFile(metadata.fileKey, file.bytes)
//
//        // 3- Persistir a metadata no banco
//        val savedMeta = FileMetadataRepository.save(metadata)
//
//        // 4 -Criar candidato com a referência ao metadata
//        val candidate = Candidate(
//            name = "Novo Candidato", // pode vir do formulário
//            resumeFile = savedMeta
//        )
//
//        return insert(candidate, null)
//    }

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


    fun getCandidateDetails(id: Int): CandidateDetailsDTO {
        val candidate = candidateRepository.findById(id)
            .orElseThrow { RuntimeException("Candidato não encontrado") }

        return CandidateDetailsDTO(
            idCandidate = candidate.idCandidate ?: 0,
            name = candidate.name ?: "",
            phoneNumber = candidate.phoneNumber ?: "",
            email = candidate.email ?: "",
            state = candidate.state ?: "",
            education = candidate.education,
            skills = candidate.skills,
            experience = candidate.experience,
            profilePictureBase64 = candidate.profilePicture?.let {
                java.util.Base64.getEncoder().encodeToString(it)
            }
        )
    }




}