package turns

class TurnSequence(val players : List<Turn>) {
    fun start (){
        while (true) {
            for (player in players)
                player.makeTurn()
        }
    }
}