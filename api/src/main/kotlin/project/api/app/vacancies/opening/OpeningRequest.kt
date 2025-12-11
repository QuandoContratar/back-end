package project.api.app.vacancies.opening


import jakarta.persistence.*
import project.api.app.File.data.FileMetadata
import project.api.app.users.data.User
import java.time.LocalDateTime

@Entity
@Table(name = "opening_requests")
data class OpeningRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(nullable = false)
    val cargo: String,

    @Column(nullable = false)
    val periodo: String,

    @Column(nullable = false)
    val modeloTrabalho: String,

    @Column(nullable = false)
    val regimeContratacao: String,

    @Column(nullable = false)
    val salario: Double,

    @Column(nullable = false)
    val localidade: String,

    @Column(columnDefinition = "TEXT")
    val requisitos: String? = null,

//    @ManyToOne
//    @JoinColumn(name = "justificativa_file_id")
//    val justificativaFile: FileMetadata? = null,

    val justificativaPath: String? = null, // caminho do arquivo enviado

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gestor_id")
    val gestor: User,

    val area: String,

    @Enumerated(EnumType.STRING)
    var status: OpeningStatus = OpeningStatus.ENTRADA,

    val createdAt: LocalDateTime = LocalDateTime.now()


)

enum class OpeningStatus {
    ENTRADA, ABERTA, APROVADA, REJEITADA, CANCELADA
}
