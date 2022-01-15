package coords

data class CoordString (val value: String){

    var letter = value[0].toString()
    var number = value.substring(1)
}