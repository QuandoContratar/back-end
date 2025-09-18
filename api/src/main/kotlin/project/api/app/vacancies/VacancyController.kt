package project.api.app.vacancies

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.api.app.vacancies.data.Vacancy
import project.api.core.CrudController

@RestController
@RequestMapping("/vacancies")
class VacancyController(
    val vacancyService: VacancyService
): CrudController<Vacancy>(vacancyService)