package turns

import coordinates.GetCoord
import fields.TechField

// Осуществляет ходы
class Turn(var techField: TechField, var isHuman: Boolean) {

    var counter = 0 // Счетчик ходов

    fun makeTurn(): Pair<Boolean, Int> { // true - игра продложается
        counter++
        println("\nХод № $counter")
        var isScored = false // Признак, что "попал" в корабль
        val turnCoord =
            if (isHuman) GetCoord().turnHuman() else GetCoord().turnRobot(techField) // Получаем координату хода
        val target = techField.fieldArray[turnCoord.number][turnCoord.letter] // Код на ТехПоле с такой координатой
        when {
            target > 10 && target < 50 -> { // Если код на ТехПоле - это id корабля
                isScored = true // то "попал"
                techField.scoredList.add(turnCoord) // Добавлем координату в коллекцию, где клетки, в которые "попал"
                val boat = techField.boatList[target] // Из коллекции кораблей берем корабль по id
                boat?.liveMinus() // Сокращаем жизнь
                if (boat?.lives!! == 0) { // Если убит
                    println("Убит")
                    for (coord in boat.frame) // добавляем рамку корабля в коллекцию с полями "мимо"
                        techField.failList.add(coord!!)
                    techField.aliveBoatCounter-- // Декремент счетчика живых кораблей
                    if (techField.aliveBoatCounter == 0) { // Если живых кораблей не осталось
                        with(techField) {
                            update()
                            lastTurnCoord = turnCoord
                            for (ui in TechField.uiScene) {
                                ui.update()
                            }
                            TechField.printUI()
                        }
                        println(
                            """
                            GAME OVER
                            Сделано ходов: $counter
                            """.trimIndent()
                        )
                        return false to counter
                    }
                } else // Если попал, но остались жизни
                    println("Ранен")
            }
            target <= 0 -> { // Если поле помечено как "мимо" или как сбитый корабль
                println("Нет смысла стрелять в эту клетку")
                counter--
                return makeTurn() // Рекурсия
            }
            else -> {
                println("Мимо")
                techField.failList.add(turnCoord)
            }
        }
        // В конце попытки хода перерисовываем интерфейс
        with(techField) {
            update()
            lastTurnCoord = turnCoord
            for (ui in TechField.uiScene) {
                ui.update()
            }
            TechField.printUI()
        }
        // Если "попал" и остались живые корабли
        if (isScored && techField.aliveBoatCounter > 0) {
            counter--
            return makeTurn() // Рекурсия
        }
        return true to counter
    }
}
