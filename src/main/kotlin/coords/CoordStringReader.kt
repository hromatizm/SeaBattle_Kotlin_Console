package coords

abstract class CoordStringReader {
    abstract val text : String
    fun read(): String? {
        val pattern = "_?[а-иА-ИкК]([1-9]|10)"
        val pat = pattern.toRegex()
        var line: String?
        print(text)
        do {
            line = readLine()
            val isMatch = line?.let { pat.matches(it) } as Boolean
            if (!isMatch) println("Не допустимые координаты. Попробуйте еще раз.")
        } while (!isMatch)
        return line
    }
}