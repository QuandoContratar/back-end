package project.api.app.File

import org.springframework.stereotype.Service
import project.api.core.CrudService
import project.api.app.File.data.FileMetadata

@Service
class FileMetadataService(
    repository: FileMetadataRepository
) : CrudService<FileMetadata>(repository)
