package project.api.app.File

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import project.api.app.File.data.FileMetadata

@Repository
interface FileMetadataRepository : JpaRepository<FileMetadata, Int>
