package coords

class CoordInt {
    val letter: Int
    val number: Int
    val value: Pair<Int, Int>

    constructor(letter: Int, number: Int) {
        this.letter = letter
        this.number = number
        this.value = letter to number
    }

    constructor(coordString: CoordString) {
        val cSL = coordString.letter
        this.letter = when {
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
        this.number = coordString.number.toInt()
        this.value = letter to number
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is CoordInt)
            return false
        return value == other.value
    }
}