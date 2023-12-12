package bullscows.logic.generator

abstract class CodeGenerator : Generator {

    protected abstract val characters: List<Any>

    protected abstract fun codeGenerator(codeLength: Int): Any

}