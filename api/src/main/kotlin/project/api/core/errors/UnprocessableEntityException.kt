package project.api.core.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
class UnprocessableEntityException:RuntimeException {
    constructor(entity:Class<*>):super("Unable to process data from ${entity.name}");
}