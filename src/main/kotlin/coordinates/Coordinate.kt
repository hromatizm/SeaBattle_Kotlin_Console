package coordinates

// Числовая координата клетки:
class Coordinate {
    val letter: Int // Буквенная часть
    val number: Int // Циферная часть
    val value: Pair<Int, Int>


    // Конструктор для действий робота принимает 2 числа
    constructor(letter: Int, number: Int) {
        this.letter = letter
        this.number = number
        this.value = letter to number

      }

    // Конструктор для действий человека принимает координату в строковом формате
    constructor(coordString: CoordString) {
        val cSL = coordString.letter
        this.letter = when { // Преобразует букву в число
            (cSL == "А" || cSL == "а") -> 1
            (cSL == "Б" || cSL == "б") -> 2
            (cSL == "В" || cSL == "в") -> 3
            (cSL == "Г" || cSL == "г") -> 4
            (cSL == "Д" || cSL == "д") -> 5
            (cSL == "Е" || cSL == "е") -> 6
            (cSL == "Ж" || cSL == "ж") -> 7
            (cSL == "З" || cSL == "з") -> 8
            (cSL == "И" || cSL == "и") -> 9
            (cSL == "К" || cSL == "к") -> 10
            else -> {
                println("Wrong String Coords Letter")
                0
            }
        }
        this.number = coordString.number.toInt() // Числовую часть преводит из String в Int
        this.value = letter to number

    }

    // Переопределяем фукцию для сравнения двух координат.
    // Нужно для проверки -  может ли корабль с такими координатами корректно стоять на поле.
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Coordinate)
            return false
        return (value.first == other.value.first && value.second == other.value.second)
    }


}