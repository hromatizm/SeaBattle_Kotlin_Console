package fields

// UI на время расстановки кораблей
// Содержит только информацию о клетках, где стоят корабли
// Для последюущей отрисовки после каждого нового поставленного корабля

class UIFInstaller(val techField: TechField) : UIField(techField) {
    override fun update() {
        for (boat in techField.boatList) // Перебираем коллекцию кораблей из ТехПоля
            for (coord in boat.value.coordinates)
                fieldArray[coord!!.number - 1][coord.letter - 1] = boatChar // И в UI поле добавляем значек
    }
}