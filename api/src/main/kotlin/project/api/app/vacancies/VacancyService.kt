package project.api.app.vacancies

import org.springframework.stereotype.Service
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancyDto
import project.api.core.CrudService

@Service
class VacancyService(
    val vacancyRepository: VacancyRepository,
): CrudService<Vacancy>(vacancyRepository) {
//    fun Vacancy.toDto(): VacancyDto = VacancyDto(
//        id = this.id,
//        position = this.position,
//        period = this.period,
//        workModel = this.workModel.name,
//        requirements = this.requirements,
//        contractType = this.contractType,
//        salary = this.salary,
//        location = this.location,
//        openingJustification = this.openingJustification,
//        managerName = this.manager.name!! // Só o nome, não o objeto inteiro!
//    )
}