package turns

import coords.CoordInt
import fields.TechField
import kotlin.system.exitProcess

abstract class Turn(val techField: TechField) {

    abstract fun getCoord(): CoordInt
    var counter = 0

    fun makeTurn() {
        counter++
        var isScored = false
        val coord = getCoord()
        val target = techField.fieldArray[coord.number][coord.letter]
        when {
            target > 10 && target < 50 -> {
                isScored = true
                techField.scoredList.add(coord)
                val boat = techField.boatList.get(target)
                boat?.liveMinus()
                if (boat?.lives!! == 0) {
                    println("Убит")
                    for (coord in boat.frame.coords)
                        techField.mimoList.add(coord)
                    techField.boatCounter--
                    if (techField.boatCounter == 0) {
                        techField.update()
                        techField.uiTurns.update()
                        techField.uiTurns.print()
                        println(
                            """
                            GAME OVER
                            Сделано $counter ходов
                            """.trimIndent()
                        )
                        exitProcess(0)
                    }
                } else
                    println("Ранен")
            }
            target <= 0 -> {
                println("Нет смысла стрелять в эту клетку")
                makeTurn()
            }
            else -> {
                println("Мимо")
                techField.mimoList.add(coord)
                0
            }
        }
        techField.update()
        techField.uiTurns.update()
        techField.uiTurns.print()
        if (isScored && techField.boatCounter > 0)
            makeTurn()
    }
}