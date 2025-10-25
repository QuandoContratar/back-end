package project.api.app.vacancies.opening

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.api.core.CrudController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/opening-requests")
class OpeningRequestController(
    override val service: OpeningRequestService
) : CrudController<OpeningRequest>(service) {

    @GetMapping("/by-gestor/{gestorId}")
    fun findByGestor(@PathVariable gestorId: Int): ResponseEntity<List<OpeningRequest>> {
        val list = (service.repository as OpeningRequestRepository).findByGestorId(gestorId)
        return ResponseEntity.ok(list)
    }

    @PostMapping("/{id}/upload-justificativa")
    fun uploadJustificativa(
        @PathVariable id: Int,
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<OpeningRequest> {
        val opening = service.findById(id)
        val filePath = "uploads/justificativas/${file.originalFilename}"
        file.transferTo(java.io.File(filePath))
        val updated = opening.copy(justificativaPath = filePath)
        val saved = service.repository.save(updated)
        return ResponseEntity.ok(saved)
    }

//@PostMapping
  //  fun create(@RequestBody request: OpeningRequest): ResponseEntity<OpeningRequest> {
      //  return ResponseEntity.ok(service.create(request))
//}

    @GetMapping("/status/{status}")
    fun listByStatus(@PathVariable status: OpeningStatus): ResponseEntity<List<OpeningCardDTO>> {
        val list = service.listByStatus(status)
        return ResponseEntity.ok(list)
    }

    @PatchMapping("/{id}/status/{status}")
    fun updateStatus(
        @PathVariable id: Int,
        @PathVariable status: OpeningStatus
    ): ResponseEntity<OpeningRequest> {
        val updated = service.moveToStatus(id, status)
        return ResponseEntity.ok(updated)
    }

//    @DeleteMapping("/{id}")
//    override fun delete(@PathVariable id: Int): ResponseEntity<Void> {
//        service.delete(id)
//        return ResponseEntity.noContent().build()
//    }

//    @GetMapping("/search")
//    fun search(@RequestParam query: String): ResponseEntity<List<OpeningCardDTO>> {
//        val results = (service.repository as OpeningRequestRepository).search(query)
//            .map { OpeningCardDTO.fromEntity(it) }
//        return ResponseEntity.ok(results)
//    }



//    @PostMapping("/{id}/upload-justificativa")
//    fun uploadJustificativa(
//        @PathVariable id: Int,
//        @RequestParam("file") file: MultipartFile
//    ): ResponseEntity<OpeningRequest> {
//        val opening = service.findById(id)
//
//        val uploadDir = java.io.File("uploads/justificativas")
//        if (!uploadDir.exists()) uploadDir.mkdirs() // criar pasta se não existir
//
//        val filePath = "${uploadDir.absolutePath}/${file.originalFilename}"
//        val destination = java.io.File(filePath)
//
//        file.transferTo(destination)
//
//        val updated = opening.copy(justificativaFile = null) // ou talvez criar um FileMetadata aqui
//        val saved = service.repository.save(updated)
//
//        return ResponseEntity.ok(saved)
//    }


}
