package project.api.app.vacancies

import org.springframework.stereotype.Service
import project.api.app.users.data.User
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancyDto
import project.api.app.vacancies.data.VacancySummaryDTO
import project.api.core.CrudService

@Service
class VacancyService(
    val vacancyRepository: VacancyRepository,
): CrudService<Vacancy>(vacancyRepository) {

    fun listVacancies(): List<VacancySummaryDTO> {
        return vacancyRepository.findActiveVacancies()
    }


    fun uploadAllVacancies(vacancies: List<Vacancy>, gestor: User) {
    val newVacancies = vacancies.map { vacancy ->
        Vacancy(
            position_job = vacancy.position_job,
            period = vacancy.period,
            workModel = vacancy.workModel,
            requirements = vacancy.requirements,
            contractType = vacancy.contractType,
            salary = vacancy.salary,
            location = vacancy.location,
            openingJustification = vacancy.openingJustification,
            area = vacancy.area,
            manager = gestor
        )
    }
        println("passou 2")
    vacancyRepository.saveAll(newVacancies)
}

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