package bullscows.ui

import bullscows.data.GameState

/**
 * Class responsible for handling the user interface of the Bulls and Cows game.
 *
 * @property gameState The current state of the game.
 */
class GameUI(private val gameState: GameState) {

    /**
     * Reads the user's input for their guess and updates the game state.
     */
    fun readUserGuess() {
        gameState.userGuess = readln()
    }

    fun readCodeLength(): Int? {
        println("Input the length of the secret code:")
        val input = readln()

        val codeLength = input.toIntOrNull()

        if (codeLength == null) {
            println("Error: \"$input\" isn't a valid number.")
            gameState.status = GameState.Status.ENDED
        } else {
            if (codeLength <= 0) {
                println("Error: The length of the secret code must be a positive number.")
                gameState.status = GameState.Status.ENDED
            }
        }

        return codeLength
    }



    fun turnCount(turn: Int) {
        println("Turn $turn:")
    }

    fun possibleSymbols() {
        println("Input the number of possible symbols in the code:")
        gameState.possibleSymbols = readln().toInt()

        if (gameState.codeLength > gameState.possibleSymbols) {
            println("Error: it's not possible to generate a code with a length of ${gameState.codeLength} with ${gameState.possibleSymbols} unique symbols.")
            gameState.status = GameState.Status.ENDED
        }

        if (gameState.possibleSymbols > 36) {
            println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).")
            gameState.status = GameState.Status.ENDED
        }
    }

    fun announceStart() {
        println("Okay, let's start a game!")
    }


}