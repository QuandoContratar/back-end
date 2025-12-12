package project.api.app.candidates

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.api.app.candidates.CandidateIngestService
import project.api.app.candidates.dto.CandidateProfileDTO

@RestController
@RequestMapping("/candidate")
class CandidateIngestController(
    private val ingestService: CandidateIngestService
) {
    @PostMapping("/ingest")
    fun ingestCandidate(
        @RequestParam vacancyId: Long,
        @RequestBody dto: CandidateProfileDTO
    ): ResponseEntity<Any> {

        val saved = ingestService.ingest(dto, vacancyId)

        return ResponseEntity.ok(
            mapOf(
                "message" to "Candidate successfully ingested",
                "candidateId" to saved.idCandidate,
                "email" to saved.email,
                "name" to saved.name
            )
        )
    }

//    @PostMapping("/ingest")
//    fun ingestCandidate(@RequestBody dto: CandidateProfileDTO): ResponseEntity<Any> {
//
//        val saved = ingestService.ingest(dto)
//
//        return ResponseEntity.ok(
//            mapOf(
//                "message" to "Candidate successfully ingested",
//                "candidateId" to saved.idCandidate,
//                "email" to saved.email,
//                "name" to saved.name
//            )
//        )
//    }
}
