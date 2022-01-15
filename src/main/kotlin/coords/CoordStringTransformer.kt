package coords

class CoordStringTransformer {
    fun transform(line: String): Pair<CoordInt, Boolean> {
        val isVertical = line.first() != '_'
        val lineHorizont = line.substring(1)

        val coordString = when{
            isVertical-> CoordString(line)
            else-> CoordString(lineHorizont)
        }
        val coordInt = CoordInt(coordString)
        return coordInt to isVertical
    }
}