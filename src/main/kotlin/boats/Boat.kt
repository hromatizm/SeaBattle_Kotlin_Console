package boats

import coordinates.Coordinate

class Boat(val id: Int, val coordBegin: Coordinate, val isVertical: Boolean) {
    /*
        id кораблей:
        41 - четырехпалубный корабль
        31, 32 - трехпалубные корабли
        21, 22, 23 - двухпалубные корабли
        11, 12, 13, 14 - однопалубные корабли
     */
    // Размер корабля - первая цифра id:
    open val size: Int = id.toString()[0].toString().toInt()

    // Кол-во "жизней" корабля, для фиксации состояния "убит":
    open var lives: Int = size

    // Координата конца корабля нужна для проверки, что корабль не выходит за пределы поля:
    val coordEnd: Coordinate

    // Коллекция всех координат корабля:
    val coordinates: Array<Coordinate?> = arrayOfNulls(size)

    // Рамка вокруг корабля (туда не могут быть поставлены другие корабли, и там "мимо" после убийства корабля):
    val frame: Array<Coordinate?> = arrayOfNulls(size * 2 + 6)

    init {
        coordinates[0] = coordBegin
        // Если размер корабля больше чем 1, то добавляем в коллекцию остальные координаты:
        if (size > 1) {
            for (i in 1 until size) {
                val last = coordinates[i - 1] // Берем последнюю координату из коллекции
                val new = when { // На ее основе создаем новую
                    isVertical -> Coordinate(last!!.letter, last.number + 1)
                    else -> Coordinate(last!!.letter + 1, last.number)
                }
                coordinates[i] = (new)
            }
        }
        coordEnd = coordinates[size - 1]!!
    }

    fun liveMinus() {
        lives--
    }

    // Вывод координат в консоль. Для отладки.
    fun print() {
        for (coord in this.coordinates) {
            val l: Int = coord!!.letter
            val n: Int = coord.number
            println("$l$n")
        }
        println(isVertical)
    }
}