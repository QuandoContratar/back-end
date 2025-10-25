package project.api.app.File.data

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
import java.time.LocalDateTime

@Entity
@Table(name = "file_metadata")
data class FileMetadata(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idFile: Int? = null,

    @Column(nullable = false)
    val fileName: String,

    val fileType: String? = null, // pdf, docx, png...

    val bucket: String? = "local", // local, s3, azure_blob

    @Enumerated(EnumType.STRING)
    var status: FileStatus = FileStatus.ATIVO,

    @Enumerated(EnumType.STRING)
    var action: FileAction = FileAction.UPLOAD,

    val fileKey: String? = null, // caminho ou chave única

    @ManyToOne
    @JoinColumn(name = "id_user_upload")
    val userUpload: User? = null,

    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class FileStatus { ATIVO, INATIVO, EXCLUIDO }
enum class FileAction { UPLOAD, DOWNLOAD, DELETE }
