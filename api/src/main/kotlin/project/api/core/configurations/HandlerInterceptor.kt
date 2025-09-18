package project.api.core.configurations

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

class HandlerInterceptor: HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println("Endpoint called: ${request.requestURI}, Method: ${request.method}")
        return true
    }
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        println("Request response to ${request.requestURI}: ${response.status}")
    }
}