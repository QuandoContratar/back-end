package project.api.app.vacancies

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import project.api.app.vacancies.data.Vacancy
import project.api.app.vacancies.data.VacancySummaryDTO

@Repository
interface VacancyRepository:JpaRepository<Vacancy, Int> {



    @Query("""
    SELECT v.position_job, v.workModel, v.manager.name, v.area
    FROM Vacancy v
""")
    fun findActiveVacancies(): List<VacancySummaryDTO>











}