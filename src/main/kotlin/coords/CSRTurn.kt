package coords

// Наследник CoordStringReader. Для ввода координат выстрелов
class CSRTurn : CoordStringReader() {
    override val text = """
            Ваш ход. Введите координаты клетки.
            
            """.trimIndent()
}