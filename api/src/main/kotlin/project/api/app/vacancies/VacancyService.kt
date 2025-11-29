package project.api.app.vacancies

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import project.api.app.candidates.data.Candidate
import project.api.app.users.data.User
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancyDto
import project.api.app.vacancies.data.VacancyOpeningDTO
import project.api.app.vacancies.data.VacancySummaryDTO
import project.api.core.CrudService

@Service
class VacancyService(
    val vacancyRepository: VacancyRepository,
): CrudService<Vacancy>(vacancyRepository) {

    fun listVacancies(): List<VacancySummaryDTO> {
        return vacancyRepository.findActiveVacancies()
    }


    fun uploadAllVacanciesPart1(vacancies: List<VacancyOpeningDTO>, gestor: User): List<Int> {
        val newVacancies = vacancies.mapIndexed { index, dto ->
            Vacancy(
                position_job = dto.position_job,
                period = dto.period,
                workModel = dto.workModel,
                requirements = dto.requirements,
                contractType = dto.contractType,
                salary = dto.salary,
                location = dto.location,
//                openingJustification = filesOpening.getOrNull(index),
                area = dto.area,
                manager = gestor,
                statusVacancy = "pendente aprovação"
            )
    }
        println("passou 2")
    val vacanciesAll = vacancyRepository.saveAll(newVacancies)
        val ids = vacanciesAll.mapNotNull { it.id }
        return ids

}

    @Transactional
    open fun uploadAllVacanciesPart2(
        files: List<ByteArray>,
        ids: List<Int>
    ): List<Vacancy> {

        if (files.size != ids.size) {
            throw IllegalArgumentException("A lista de arquivos deve ter o mesmo tamanho da lista de IDs.")
        }

        return ids.mapIndexed { index, id ->

            // busca a entidade
            val vacancy = vacancyRepository.findById(id)
                .orElseThrow { RuntimeException("Vacancy $id não encontrada") }

            // altera somente o campo necessário
            vacancy.openingJustification = files[index]

            // salva e retorna a entidade atualizada
            vacancyRepository.save(vacancy)
        }
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