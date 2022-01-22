package prog

fun main() {
    TwoPlayersGame().start()

//    var difficulty = 100
//
//    val turnCounterMap = mutableMapOf<Int, ArrayList<Int>>()
//
//
//    val field = TechField()
//    val factory = BoatFactory(field)
//    val installer = BoatInstaller(factory, false)
//    field.uiInstaller.print()
//    installer.installAll(listOf(41, 31, 32, 21, 22, 23, 11, 12, 13, 14))
//
//
//    val robot = Turn(field, false)
//    val players = listOf(robot)
//    TurnSequence(players).start()

//    Проверка глубины сложности:
//    val difficultyList = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 25, 30, 50, 100, 150, 300, 500, 1000, 1500, 2000)
//    for (i in 1..50) {
//        for (difficulty in difficultyList) {
//            println("$difficulty - $i")
//            field.uiTurns.print()
//
//
//            var turnCounter = (TurnSequence(players).start())
//            if(turnCounterMap.any {it.key == difficulty})
//                turnCounterMap[difficulty]?.add(turnCounter)
//            else turnCounterMap.put(difficulty,arrayListOf(turnCounter))
//            field.clearField()
//        }
//    }
//    println(turnCounterMap.size)
//    turnCounterMap.forEach { println("${it.key}: мин - ${it.value.minOrNull()} сред - ${it.value.average()} макс - ${it.value.maxOrNull()}") }
}