package boats

import coords.CSRBoats

// Расстановщик кораблей на поле
open class BoatInstaller(private val factory: BoatFactory) {
    // Приглашение поставить корабль:
    open fun printWelcome(size: Int, num: Int) {
        print("\nПоставим ")
        if (size < 4)
            print("$num-й ")
        print("$size-палубный...\n")
    }

    open fun printError() {
        println("Не корректные координаты")
    }
    // Установка одного корабля. Принимает в качесвте параметра только id корабля
    fun install(id: Int): Boat {
        val size = id / 10 // Размер корабля - первая цифра id
        val num = id - size * 10 // Номер корабля данного размера - вторая цифра id
        var boat: Boat
        do {
            printWelcome(size, num) // Сообщение с приглашением поставить корабль
            val readedPair = CSRBoats().read() // Считываем из консоли пару
            val coordBegin = readedPair.first // Первый элемент пары - начальная координата корабля
            val isVertical = readedPair.second // Второй элемент пары - признак вертикальности
            boat = factory.makeBoat(id, coordBegin, isVertical) // Через фабрику делаем корабль
            if (!testBoat(boat)) // Проверка - может ли корабль с такими координатами корректно стоять на поле.
                printError()
        } while (!testBoat(boat))
        // Если проверка пройдена, то ставим его на поле:
        with(factory.techField) {  // На ТехПоле
            boatList.put(boat.id, boat) // Добавлем в коллекцию кораблей
            update() // Обновляем ТехПоле
            uiInstaller.update() // Обновляем UI поле
            aliveBoatCounter++ // Инкремент счетчика кораблей
        }
        return boat
    }

    fun installAll() { // Установка всех нужных кораблей:
        val boatsIdToInstall = listOf(41, 31, 32, 21, 22, 23, 11, 12, 13, 14) // Список id кораблей для установки
        for (id in boatsIdToInstall) {
            install(id)
            factory.techField.uiInstaller.print() // Выводим UI поле в консоль
        }
        for (i in 1..50) // Печатаем пустые строки, чтобы "очистить" консоль
            println("\n")
    }

    // Проверка - может ли корабль с такими координатами корректно стоять на поле:
    fun testBoat(boat: Boat): Boolean {
        if (boat.coordEnd.letter > 10 || boat.coordEnd.number > 10) { // Последняя координата не больше 10
            return false
        }
        if (this.factory.techField.boatList.size == 0) // Если ставиться первый корабль, то ОК.
            return true

        for (coord in boat.coordinates) {  // Координаты корбля
            for (boatOnField in this.factory.techField.boatList) { // Уже стоящие корабли
                // Сравниваем координаты корабля с координатами поставленных кораблей:
                for (coordBF in boatOnField.value.coordinates) {
                    if (coord == coordBF)
                        return false

                }
                // Сравниваем координаты корабля с координатами рамок поставленных кораблей:
                for (coordFrame in boatOnField.value.frame) {
                    if (coord == coordFrame)
                        return false
                }
            }
        }
        return true
    }
}