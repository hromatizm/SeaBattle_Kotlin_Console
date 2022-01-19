package coords

// Наследник CoordStringReader. Для ввода координат кораблей
class CSRBoats : CoordStringReader() {
    override val text = """
            Введите первую (верхнюю левую) координату корабля в формате <буква><цифра> (например: Б5).
            Если корабль должен быть горизонтальным, то поставте перед координатами знак _ (нижнее подчеркивание),
            иначе корабль будет веритакльным.
            
            """.trimIndent()
}