package coordinates

// Временно хранит координату в строковом формате (после ввода человеком из консоли)
data class CoordString(val value: String) {

    var letter = value[0].toString()
    var number = value.substring(1)
}