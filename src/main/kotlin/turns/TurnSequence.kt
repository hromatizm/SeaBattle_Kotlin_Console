package turns

// Поочередные ходы. Получает в качестве параметра коллекцию типа Turn
class TurnSequence(val players: List<Turn>) {

    // Ходы делаются по очереди до тех пор, пока не неступит GAME OVER в каком-то из Turn в коллекции.
    fun start(): Int {
        var isGoingOn: Boolean = false
        var turnCounter: Int = 0
        var result = false to 0
        do {
            for (player in players) {
                result = player.makeTurn()
                isGoingOn = result.first
                turnCounter = result.second
            }
        } while (isGoingOn)
        return turnCounter
    }
}