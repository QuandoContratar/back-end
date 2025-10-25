package project.api.app.vacancies.opening

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import project.api.core.CrudService
import org.springframework.web.bind.annotation.PathVariable

@Service
class OpeningRequestService(
    override val repository: OpeningRequestRepository
) : CrudService<OpeningRequest>(repository) {

    fun create(request: OpeningRequest): OpeningRequest {
        return repository.save(request)
    }


    fun listByStatus(status: OpeningStatus): List<OpeningCardDTO> {
        return repository.findByStatus(status)
            .map { OpeningCardDTO.fromEntity(it) }
    }

    fun moveToStatus(id: Int, newStatus: OpeningStatus): OpeningRequest {
        val opening = findById(id)
        val updated = opening.copy(status = newStatus)
        return repository.save(updated)
    }





}

