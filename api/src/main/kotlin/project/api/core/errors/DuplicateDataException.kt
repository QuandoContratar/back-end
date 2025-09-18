package project.api.core.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import project.api.core.enums.EntitiesEnum

@ResponseStatus(code = HttpStatus.CONFLICT)
class DuplicateDataException:RuntimeException {
    constructor(entity: EntitiesEnum):super("Duplicate data in ${entity.entityName}!");
}