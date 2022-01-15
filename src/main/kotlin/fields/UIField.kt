package fields

abstract class UIField(private val techField: TechField) {

    private val pC = 9810.toChar()
     val pointChar = "$pC"
    private val bC = 9899.toChar()
     val boatChar = "[$bC]"
    private val dC = 9760.toChar()
     val deadChar = "[$dC]"
    private val mC = 9898.toChar()
     val mimoChar = "$mC"
    private val spaceChar = 6288.toChar()
    val red = "\u001b[31m"
    val green = "\u001b[32m"
    val blue = "\u001b[34m"
    val gray = "\u001b[34m"
    val cyan = "\u001b[34m"

    // Resets previous color codes
    val reset = "\u001b[0m"

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

    protected val fieldArray = arrayOf(
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
    private val strLetters = arrayOf("    ", "А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", " ")

    abstract fun update()

    fun print() {
        for (item in strLetters)
            print("%-3s".format(item))
        println()
        var i = 1
        for (str in fieldArray) {
            print("%-3s".format(i))
            for (item in str)
                when (item) {
                    boatChar -> print("%s".format(item))
                    deadChar -> print(red + "%s".format(item) + reset)
                    mimoChar -> print(" %-2s".format(item))
                    else -> print(blue + " %-2s".format(item) + reset)
                }
            print(" %-3s".format(i))
            i++
            println()
        }
        for (item in strLetters)
            print("%-3s".format(item))
        println()
    }
}