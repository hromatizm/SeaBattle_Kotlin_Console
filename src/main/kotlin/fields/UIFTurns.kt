package fields

// UI на время выстрелов
// Содержит только информацию о клетках, где сбитые корабли и куда стреляли "мимо"
// Для последюущей отрисовки после каждого нового выстрела

class UIFTurns(val techField: TechField) : UIField(techField) {
    override fun update() {
        for (coord in techField.scoredList) // Перебираем коллекцию сбитых кораблей из ТехПоля
            fieldArray[coord.number - 1][coord.letter - 1] = deadChar // И в UI поле добавляем значек
        for (coord in techField.failList) // Перебираем коллекцию клеток "мимо" из ТехПоля
        // Т.к. ТехПоле больше UI поля на дополнительную рамку, то эту рамку исключаем обработки
            if (coord.number > 0 &&
                coord.letter > 0 &&
                coord.number < 11 &&
                coord.letter < 11
            )
                fieldArray[coord.number - 1][coord.letter - 1] = failChar // И в UI поле добавляем значек
    }
}