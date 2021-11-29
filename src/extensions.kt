import Utils.TEXTCOLOR.*

fun String.color(): String {
    return when(this.first().toString()) {
        "." -> GREEN
        "M" -> BLUE
        "T" -> YELLOW
        "A" -> RED
        else -> RESET
    }.value + this + RESET.value
}

fun String.addTab(): String = this + "\t".repeat(kotlin.math.ceil((8 - this.length) / 4.0).toInt())
