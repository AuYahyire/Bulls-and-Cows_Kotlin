package bullscows

import bullscows.data.GameState
import bullscows.logic.GameGrader
import bullscows.logic.generator.AlphanumericGenerator
import bullscows.logic.generator.Generator
import bullscows.logic.generator.NumberGenerator
import bullscows.ui.GameUI


/**
 * Class responsible for managing the overall game flow in the Bulls and Cows game.
 *
 * @property gameState The current state of the game.
 * @property gameUI The user interface for interacting with the game.
 * @property grader The grader responsible for evaluating user guesses.
 * @property generator The number generator for creating the secret code.
 */
class GameManager(
    private val gameState: GameState,
    gameUI: GameUI = GameUI(gameState),
    grader: GameGrader = GameGrader(gameState),
    generator: Generator = AlphanumericGenerator(gameState)
) {

    /**
     * Initializes the game and runs the game loop until it reaches the end.
     * Note: The loop intentionally ends after the first iteration for testing purposes.
     */
    init {
        // Read the desired length of the secret code from the user
        gameState.codeLength = gameUI.readCodeLength() ?: -1
        val codeLength = gameState.codeLength

        if (checkGameStatus()) {
            gameUI.possibleSymbols()
        }

        // Generate the secret code
        if (checkGameStatus()) {
            generator.generateCode(codeLength)
        }

        // Initialize turn count
        var turn: Int = 1

        // Game loop: Continue until the game ends
        while (checkGameStatus()) {
            if (gameState.status != GameState.Status.ENDED && turn == 1) {
                gameUI.announceStart()
            }
            // Display the current turn count
            gameUI.turnCount(turn)

            // Read the user's guess from the input
            gameUI.readUserGuess()

            // Grade the user's guess
            grader.gradeUserGuess()

            // Move to the next turn
            turn++
        }
    }

    /**
     * Checks the current status of the game.
     *
     * @return True if the game is still playing, false if it has ended.
     */
    private fun checkGameStatus(): Boolean {
        return when (gameState.status) {
            GameState.Status.PLAYING -> true
            GameState.Status.ENDED -> false
        }
    }
}

