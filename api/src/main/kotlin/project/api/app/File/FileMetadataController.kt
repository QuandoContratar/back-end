package project.api.app.File

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.api.core.CrudController
import project.api.app.File.data.FileMetadata

@RestController
@RequestMapping("/files")
class FileMetadataController(
    service: FileMetadataService
) : CrudController<FileMetadata>(service)
