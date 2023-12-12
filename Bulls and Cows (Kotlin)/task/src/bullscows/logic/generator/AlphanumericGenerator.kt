package bullscows.logic.generator

import bullscows.data.GameState

/**
 * AlphanumericGenerator is a class that generates alphanumeric codes based on the given GameState.
 *
 * @property gameState The current state of the game, providing necessary information for code generation.
 */
class AlphanumericGenerator(private val gameState: GameState) : CodeGenerator() {
    private val numbers = ('0'..'9').toList()
    private val letters = ('a'..'z').toList()

    override var characters = numbers + letters

    /**
     * Generates a secret code based on the provided [codeLength].
     *
     * @param codeLength The length of the secret code to generate.
     * @return The generated secret code or an empty string if there are not enough unique digits.
     */
    override fun codeGenerator(codeLength: Int): String {
        characters = characters.subList(0, gameState.possibleSymbols)
        return if (codeLength > characters.size) {
            println("Error: can't generate a secret number with a length of $codeLength because there aren't enough unique digits.")
            gameState.status = GameState.Status.ENDED
            ""
        } else {
            println("The secret code is prepared: ${"*".repeat(codeLength)} (${characters.first()}-${characters.last()}).")

            // Generate a code by shuffling and selecting unique digits.
            characters.shuffled().distinct().take(codeLength).joinToString("")
        }
    }

    /**
     * Generates and sets the secret code in the game state.
     *
     * @param codeLength The length of the secret code to generate.
     */
    override fun generateCode(codeLength: Int) {
        gameState.secretCode = codeGenerator(codeLength)
    }
}
