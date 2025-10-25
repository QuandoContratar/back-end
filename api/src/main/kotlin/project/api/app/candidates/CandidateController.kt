package project.api.app.candidates

import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import project.api.app.Selection.data.CandidateCardDTO
import project.api.app.candidates.data.Candidate
import project.api.app.candidates.data.CandidateDetailsDTO
import project.api.core.CrudController
import project.api.core.utils.FileMediaTypeResolver
import project.api.core.utils.FileStorageService

@RestController
@RequestMapping("/candidates")

class CandidateController (
    private val candidateService: CandidateService,
    private val FileStorageService: FileStorageService
): CrudController<Candidate>(candidateService){


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

}