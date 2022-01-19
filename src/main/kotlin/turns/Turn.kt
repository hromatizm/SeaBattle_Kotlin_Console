package turns

import coords.Coordinate
import fields.TechField
import kotlin.system.exitProcess

// Осуществляет ходы
abstract class Turn(val techField: TechField) {

    // Наследники отличаются только способом получения координаты: человек - вводит в консоли, робот - автоматически
    abstract fun getCoord(): Coordinate

    var counter = 0 // Счетчик ходов

    fun makeTurn() {
        counter++
        var isScored = false // Признак, что "попал" в корабль
        val turnCoord = getCoord() // Получаем координату хода
        val target = techField.fieldArray[turnCoord.number][turnCoord.letter] // Код на ТехПоле с такой координатой
        when {
            target > 10 && target < 50 -> { // Если код на ТехПоле - это id корабля
                isScored = true // то "попал"
                techField.scoredList.add(turnCoord) // Добавлем координату в коллекцию, где клетки, в которые "попал"
                val boat = techField.boatList.get(target) // Из коллекции кораблей берем корабль по id
                boat?.liveMinus() // Сокращаем жизнь
                if (boat?.lives!! == 0) { // Если убит
                    println("Убит")
                    for (coord in boat.frame) // добавляем рамку корабля в коллекцию с полями "мимо"
                        techField.failList.add(coord)
                    techField.aliveBoatCounter-- // Декремент счетчика живых кораблей
                    if (techField.aliveBoatCounter == 0) { // Если живых кораблей не осталось
                        techField.update()
                        techField.uiTurns.update()
                        techField.uiTurns.print() // Выводим итоговое UI поле
                        println(
                            """
                            GAME OVER
                            Сделано ходов: $counter
                            """.trimIndent()
                        )
                        exitProcess(0) // Завершение программы
                    }
                } else // Если попал, но остались жизни
                    println("Ранен")
            }
            target <= 0 -> { // Если поле помечено как "мимо" или как сбитый корабль
                println("Нет смысла стрелять в эту клетку")
                counter--
                makeTurn() // Рекурсия
            }
            else -> {
                println("Мимо")
                techField.failList.add(turnCoord)
            }
        }
        // В конце попытки хода перерисовываем интерфейс
        techField.update()
        techField.uiTurns.update()
        techField.uiTurns.print()
        // Если "попал" и остались живые корабли
        if (isScored && techField.aliveBoatCounter > 0)
            makeTurn() // То рекурсия
    }
}