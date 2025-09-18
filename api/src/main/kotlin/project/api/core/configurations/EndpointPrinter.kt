package project.api.core.configurations

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import project.api.core.enums.TerminalColorsEnum

@Component
class EndpointListener(
    val handlerMapping: RequestMappingHandlerMapping
):ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println("${TerminalColorsEnum.PURPLE}Endpoints available in application:${TerminalColorsEnum.DEFAULT}")
        handlerMapping.handlerMethods.forEach { (details, handler) ->
            val endpoint:String; // montar a string aq
            val method = handler.method;
            val methodClass = method.declaringClass.simpleName;
            val methodName = method.name;

            val methodRequest = details.methodsCondition.methods
            val defaultValues = details.patternValues
            val coloredMethodRequest = when {
                methodRequest.contains(RequestMethod.GET) -> "${TerminalColorsEnum.BLACK}GET${TerminalColorsEnum.DEFAULT}"
                methodRequest.contains(RequestMethod.POST) -> "${TerminalColorsEnum.GREEN}POST${TerminalColorsEnum.DEFAULT}"
                methodRequest.contains(RequestMethod.PATCH) -> "${TerminalColorsEnum.YELLOW}PUT${TerminalColorsEnum.DEFAULT}"
                methodRequest.contains(RequestMethod.PUT) -> "${TerminalColorsEnum.ORANGE}PUT${TerminalColorsEnum.DEFAULT}"
                methodRequest.contains(RequestMethod.DELETE) -> "${TerminalColorsEnum.RED}DELETE${TerminalColorsEnum.DEFAULT}"
                else -> {
                    if ("/error" in defaultValues)"${TerminalColorsEnum.BLACK}ERROR${TerminalColorsEnum.DEFAULT}"
                    "${TerminalColorsEnum.WHITE}OTHER${TerminalColorsEnum.DEFAULT}"
                }
            }
            println("- $coloredMethodRequest, $defaultValues: $methodClass.$methodName()")
        }
    }
}

// outra forma de fazer
//@Component
//class EndpointsListener : ApplicationListener<ContextRefreshedEvent> {
//    override fun onApplicationEvent(event: ContextRefreshedEvent) {
//        val applicationContext: ApplicationContext = event.applicationContext
//        applicationContext.getBean(RequestMappingHandlerMapping::class.java).getHandlerMethods()
//            .forEach { (info, handler) ->
//                val method = (handler as HandlerMethod).method
//                val className = method.declaringClass.simpleName
//                val methodName = method.name
//                println("- ${info}: $className.$methodName()")
//            }
//    }
//}