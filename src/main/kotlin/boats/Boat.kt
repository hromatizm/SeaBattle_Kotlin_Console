package boats

import coords.CoordInt

class Boat(val id: Int, val coordBegin: CoordInt, val isVertical: Boolean) {
/*
    id кораблей:
    41 - четырехпалубный корабль
    31, 32 - трехпалубные корабли
    21, 22, 23 - двухпалубные корабли
    11, 12, 13, 14 - однопалубные корабли
 */
val size: Int = id.toString()[0].toString().toInt()
    var lives: Int = size
    val coordEnd: CoordInt
    val coords = mutableListOf<CoordInt>()
    lateinit var frame: BoatFrame

    init {
        coords.add(coordBegin)
        if (size > 1) {
            for (coord in 2..size) {
                val last = coords.last()
                val new = when {
                    isVertical -> CoordInt(last.letter, last.number + 1)
                    else -> CoordInt(last.letter + 1, last.number)
                }
                coords.add(new)
            }
        }
        coordEnd = coords.last()
    }
fun liveMinus (){
    lives--
}
    fun print() {
        for (coord in this.coords) {
            val l: Int = coord.letter
            val n: Int = coord.number
            println("$l$n")
        }
        println(isVertical)
    }
}