package bullscows.logic

import bullscows.data.GameState
/**
 * Class responsible for grading the user's guess in the Bulls and Cows game.
 *
 * @property gameState The current state of the game.
 */
class GameGrader(private val gameState: GameState) {
    private var bulls = 0
    private var cows = 0

    /**
     * Grades the user's guess.
     */
    fun gradeUserGuess() {
        if (isEqualToSecretCode()) {
            // If the guess is correct, set bulls to the length of the secret code
            bulls = gameState.secretCode.length
            println(determineOutput())
            // Display a congratulatory message and end the game
            winCondition()
        } else {
            // If the guess is incorrect, evaluate characters and print the result
            evaluateChars()
            println(determineOutput())
        }
        // Reset bulls and cows counts for the next guess
        restartGrade()
    }

    private fun winCondition() {
        // Display a congratulatory message and set the game status to ENDED
        println("Congratulations! You guessed the secret code.")
        gameState.status = GameState.Status.ENDED
    }

    /**
     * Checks if the user's guess is equal to the secret code.
     *
     * @return True if the guess is equal, false otherwise.
     */
    private fun isEqualToSecretCode(): Boolean {
        return gameState.secretCode.contentEquals(gameState.userGuess)
    }

    /**
     * Checks if a character is present in the secret code.
     *
     * @param char The character to check.
     * @return True if the character is present, false otherwise.
     */
    private fun isCharInsideSecretCode(char: Char): Boolean {
        return gameState.secretCode.contains(char)
    }

    /**
     * Checks if a character is in the same index in the user's guess and secret code.
     *
     * @param charIndex The index of the character in the user's guess.
     * @param char The character to check.
     * @return True if the characters are in the same index, false otherwise.
     */
    private fun isCharInTheSameIndex(charIndex: Int, char: Char): Boolean {
        return gameState.secretCode[charIndex] == char
    }

    /**
     * Evaluates each character in the user's guess and updates the bulls and cows count.
     */
    private fun evaluateChars() {
        for ((index, char) in gameState.userGuess.withIndex()) {
            if (isCharInsideSecretCode(char)) {
                if (isCharInTheSameIndex(index, char)) {
                    bulls++
                } else {
                    cows++
                }
            }
        }
    }

    /**
     * Determines the output string based on the bulls and cows count.
     *
     * @return The formatted output string.
     */
    private fun determineOutput(): String {
        val output = StringBuilder()
        output.append("Grade: ")
        if (bulls > 0) {
            output.append("$bulls ${"bull".pluralize(bulls)}")
        }
        if (bulls > 0 && cows > 0) {
            output.append(" and ")
        }
        if (cows > 0) {
            output.append("$cows ${"cow".pluralize(cows)}")
        }
        if (bulls + cows == 0) {
            output.append("None")
        }

        return output.toString()
    }

    /**
     * Restarts the grading process by resetting the bulls and cows count.
     */
    private fun restartGrade() {
        bulls = 0
        cows = 0
    }
}

/**
 * Extension function to pluralize a string based on the given count.
 *
 * @param int The count used to determine pluralization.
 * @return The pluralized string.
 */
fun String.pluralize(int: Int): String {
    return if (int != 1) {
        this + "s"
    } else {
        this
    }
}

