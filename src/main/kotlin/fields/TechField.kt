package fields

import boats.Boat
import coords.CoordInt

class TechField {

    /*
   Коды технического поля сражения:
   Палубы кораблей:
   41 - четырехпалубный корабль
   31, 32 - трехпалубные корабли
   21, 22, 23 - двухпалубные корабли
   11, 12, 13, 14 - однопалубные корабли
   Те же коды со знаком минус - палубы, в которые попали.
   Поля рядом с кораблями (теже коды, но с умножением на 10):
   410
   310, 320
   210, 220, 230
   110, 120, 130, 140
   Поля, куда стреляли и не попали:
   0
   Остальные поля:
   1
    */
    val uiInstaller = UIInstaller(this)
    val uiTurns = UITurns(this)
    val boatList = mutableMapOf<Int,Boat>()
    var boatCounter = 0
    val scoredList = mutableListOf<CoordInt>()
    val mimoList = mutableListOf<CoordInt>()

    private val str0 = Array(12) { 1 }
    private val str1 = Array(12) { 1 }
    private val str2 = Array(12) { 1 }
    private val str3 = Array(12) { 1 }
    private val str4 = Array(12) { 1 }
    private val str5 = Array(12) { 1 }
    private val str6 = Array(12) { 1 }
    private val str7 = Array(12) { 1 }
    private val str8 = Array(12) { 1 }
    private val str9 = Array(12) { 1 }
    private val str10 = Array(12) { 1 }
    private val str11 = Array(12) { 1 }

    val fieldArray = arrayOf(
        str0,
        str1,
        str2,
        str3,
        str4,
        str5,
        str6,
        str7,
        str8,
        str9,
        str10,
        str11,
    )
    private val strLetters = arrayOf("_", "_", "А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "_", "_")
    private val strIndex = arrayOf("_", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "_")

    fun print() {
        for (item in strIndex)
            print("%-4s".format(item))
        println()
        for (item in strLetters)
            print("%-4s".format(item))
        println()
        for (str in fieldArray) {
            print("%-4s".format(fieldArray.indexOf(str)))
            for (item in str)
                print("%-4d".format(item))
            print("%-4s".format(fieldArray.indexOf(str)))
            println()
        }
        println()
    }

    fun update() {
        for (boat in boatList) {
            for (coord in boat.value.coords)
                fieldArray[coord.number][coord.letter] = boat.value.id
        }
        for (boat in boatList) {
            for (coord in boat.value.frame.coords)
                fieldArray[coord.number][coord.letter] = boat.value.id * 10
        }
        for (coord in scoredList) {
            if(fieldArray[coord.number][coord.letter] < 0)
                continue
            else fieldArray[coord.number][coord.letter] *=-1
        }
        for (coord in mimoList) {
            fieldArray[coord.number][coord.letter] = 0
        }
    }
}