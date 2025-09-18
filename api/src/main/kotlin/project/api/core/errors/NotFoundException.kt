package project.api.core.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class NotFoundException:RuntimeException {
    constructor():super("Entity with not found!");
}