package coords

class CoordStringReader {
    fun read(): String? {
        val pattern = "_?[а-иА-ИкК]([1-9]|10)"
        val pat = pattern.toRegex()
        var line: String?
        print(
            """
            Введите первую (верхнюю левую) координату корабля в формате <буква><цифра> (например: Б5).
            Если корабль должен быть горизонтальным, то поставте перед координатами знак _ (нижнее подчеркивание),
            иначе корабль будет веритакльным.
            """
        )
        do {
            line = readLine()
            val isMatch = line?.let { pat.matches(it) } as Boolean
            if (!isMatch) println("Не допустимые координаты. Попробуйте еще раз.")
        } while (!isMatch)
        return line
    }
}