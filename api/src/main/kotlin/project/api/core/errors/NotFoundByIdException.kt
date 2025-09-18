package project.api.core.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class NotFoundByIdException:RuntimeException {
    constructor(id: Int):super("Entity with $id not found!");
}