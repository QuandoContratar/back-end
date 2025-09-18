package project.api.app.vacancies

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import project.api.app.vacancies.data.Vacancy

@Repository
interface VacancyRepository:JpaRepository<Vacancy, Int>