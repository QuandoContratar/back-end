package project.api.app.vacancies.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import project.api.app.users.data.User

@Entity
@Table(name = "vacancies")
data class Vacancy(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_vacancy: Int? = null,

    @Column(name = "position_job")
    val position_job: String? = null,

    val period: String? = null,

    @Enumerated(EnumType.STRING)
    val workModel: WorkModel? = null,

    val requirements: String? = null,

    @Enumerated(EnumType.STRING)
    val contractType: ContractType? = null,

    val salary: Double? = null,

    val location: String? = null,

    val openingJustification: String? = null,

    val area: String? = null,

    @field:ManyToOne
    @JoinColumn(name = "fk_manager")
    val manager: User? = null
)