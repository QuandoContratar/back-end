package project.api.core.utils


import java.io.File
import org.springframework.stereotype.Service

@Service
class FileStorageService {

    fun loadFile(path: String?): ByteArray {
        val file = File(path)
        if (!file.exists()) throw RuntimeException("Arquivo não encontrado: $path")
        return file.readBytes()
    }

    fun saveFile(path: String, bytes: ByteArray) {
        val file = File(path)
        file.parentFile?.mkdirs()
        file.writeBytes(bytes)
    }
}
