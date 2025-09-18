package project.api.core.enums

import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler.DEFAULT

object TerminalColorsEnum {
    val DEFAULT = "\u001B[0m" // Texto padrão

    // Cores regulares
    val BLACK = "\u001B[30m"
    val RED = "\u001B[31m"
    val GREEN = "\u001B[32m"
    val YELLOW = "\u001B[33m"
    val BLUE = "\u001B[34m"
    val PURPLE = "\u001B[35m"
    val CYAN = "\u001B[36m"
    val WHITE = "\u001B[37m"
    val ORANGE = "\u001B[38;5;208m"
}