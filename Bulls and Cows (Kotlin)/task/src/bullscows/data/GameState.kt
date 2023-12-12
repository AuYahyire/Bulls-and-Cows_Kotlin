package bullscows.data

/**
 * Data class representing the current state of the Bulls and Cows game.
 *
 * @property secretCode The secret code that the player is trying to guess. Default is "9305".
 * @property status The current status of the game, either [Status.PLAYING] or [Status.ENDED]. Default is [Status.PLAYING].
 * @property userGuess The user's current guess. Default is an empty string.
 */
data class GameState(
    var secretCode: String = "9305",
    var status: Status = Status.PLAYING,
    var userGuess: String = "",
    var possibleSymbols: Int = 1,
    var codeLength: Int = 0
) {


    /**
     * Enum representing the possible statuses of the game.
     */
    enum class Status {
        PLAYING, ENDED
    }
}

