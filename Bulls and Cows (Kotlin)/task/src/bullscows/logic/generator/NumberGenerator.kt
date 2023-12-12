package bullscows.logic.generator

import bullscows.data.GameState

/**
 * Concrete implementation of [CodeGenerator] for generating random numeric codes.
 *
 * @param gameState The current state of the game.
 */
class NumberGenerator(private val gameState: GameState) : CodeGenerator() {

    // The list of characters used for generating numeric codes.
    override val characters = ('0'..'9').toList()

    /**
     * Generates a random numeric code of the specified length.
     *
     * @param codeLength The length of the code to be generated.
     * @return The generated numeric code.
     */
    override fun codeGenerator(codeLength: Int): String {
        return if (codeLength > characters.size) {
            println("Error: can't generate a secret number with a length of $codeLength because there aren't enough unique digits.")
            gameState.status = GameState.Status.ENDED
            ""
        } else {
            // Generate a code by shuffling and selecting unique digits.
            val code = characters.shuffled().distinct().take(codeLength).joinToString("")

            // Ensure the generated code doesn't start with '0'.
            if (code.first() == '0') code.replaceFirst('0', characters.random()) else code
        }
    }

    /**
     * Generates a random numeric code and sets it as the secret code in the game state.
     *
     * @param codeLength The length of the code to be generated.
     */
    override fun generateCode(codeLength: Int) {
        gameState.secretCode = codeGenerator(codeLength)

       /* // Print the generated secret number if the game is not ended.
        if (gameState.status != GameState.Status.ENDED) {
            printResult()
        }*/
    }

    /**
     * Prints the generated secret number.
     */
    private fun printResult() {
        println("The random secret number is ${gameState.secretCode}.")
    }

}
