package turns

// Поочередные ходы. Получает в качестве параметра коллекцию типа Turn
class TurnSequence(val players: List<Turn>) {

    // Ходы делаются по очереди до тех пор, пока не неступит GAME OVER в каком-то из Turn в коллекции.
    fun start() {
        while (true) {
            for (player in players)
                player.makeTurn()
        }
    }
}