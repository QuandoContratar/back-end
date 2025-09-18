package project.api.core.utils

import org.springframework.http.HttpStatusCode
import org.springframework.web.server.ResponseStatusException

abstract class ArrayUtils {
    companion object {
        fun validateArray(list: List<*>){
            if (list.isEmpty()){
                throw ResponseStatusException(HttpStatusCode.valueOf(204));
            }
        }
    }
}