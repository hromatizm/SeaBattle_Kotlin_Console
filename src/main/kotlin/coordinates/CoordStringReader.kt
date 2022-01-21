package coordinates

abstract class CoordStringReader {

    abstract val text: String // Наследники отличаются только текстом приглашения ввести координату

    // Читает строку, вводимую человеком в консоль и возвращеет пару:
    // (1) координта корабля
    // (2) признак вертикальности:
    fun read(): Pair<Coordinate, Boolean> {
        val pattern = "_?[а-иА-ИкК]([1-9]|10)" // Регулярное выражение для проверки вводимой координаты
        val regex = pattern.toRegex()
        var line: String
        print(text) // Приглашение ввести координату
        do { // Ввод из консоли пока ни будет соответствовать регулярному варыжению
            line = readLine().toString()
            val isMatch = line.let { regex.matches(it) }
            if (!isMatch) println("Не допустимые координаты. Попробуйте еще раз.")
        } while (!isMatch)

        // Если превый введенный символ НЕ "нижнее подчеркивание", то признак вертикальности корабля - true
        val isVertical = line.first() != '_'
        val coordString = CoordString(line.replace("_","")) // Убираем "_" из строки
        val coordInt = Coordinate(coordString) // CoordString преобразуем в числовую координату

        return coordInt to isVertical
    }
}