fun main() {
    val line = readlnOrNull()
    println("Elvis says: ${line ?: throw IllegalStateException()}")
}
