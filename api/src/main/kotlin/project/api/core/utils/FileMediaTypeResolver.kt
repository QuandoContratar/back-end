package project.api.core.utils


object FileMediaTypeResolver {
    fun resolve(fileType: String?): String {
        return when (fileType?.lowercase()) {
            "pdf" -> "application/pdf"
            "doc", "docx" -> "application/msword"
            "png" -> "image/png"
            "jpg", "jpeg" -> "image/jpeg"
            else -> "application/octet-stream"
        }
    }
}
