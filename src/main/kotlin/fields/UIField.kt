package fields

class UIField(private val techField: TechField) {

    private val pointChar = 9810.toChar()
    private val boatChar = 9899.toChar()
    private val deadChar = 9760.toChar()
    private val mimoChar = 9898.toChar()
    private val spaceChar = 6288.toChar()


    private var str1 = Array(10) { pointChar }
    private var str2 = Array(10) { pointChar }
    private var str3 = Array(10) { pointChar }
    private var str4 = Array(10) { pointChar }
    private var str5 = Array(10) { pointChar }
    private var str6 = Array(10) { pointChar }
    private var str7 = Array(10) { pointChar }
    private var str8 = Array(10) { pointChar }
    private var str9 = Array(10) { pointChar }
    private var str10 = Array(10) { pointChar }

    private val fieldArray = arrayOf(
        str1,
        str2,
        str3,
        str4,
        str5,
        str6,
        str7,
        str8,
        str9,
        str10
    )
    private val strLetters = arrayOf(" ", "А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", " ")

    fun update() {
        for(boat in techField.boatList)
            for(coord in boat.coords)
                fieldArray[coord.number-1][coord.letter-1] = boatChar
    }
    fun print() {
         for (item in strLetters)
            print("%-3s".format(item))
        println()
        for (str in fieldArray) {
            print("%-3s".format(fieldArray.indexOf(str)+1))
            for (item in str)
                print("%-3s".format(item))
            print("%-3s".format(fieldArray.indexOf(str)+1))
            println()
        }
        for (item in strLetters)
            print("%-3s".format(item))
        println()
    }
}