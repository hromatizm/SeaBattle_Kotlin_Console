package boats

import coordinates.GetCoord
import fields.TechFieldForAlgoritm

// Расстановщик кораблей на поле
class BoatInstaller(private val factory: BoatFactory, private val isHuman: Boolean) {
    val techField = factory.techField

    // Приглашение поставить корабль:
    fun printWelcome(size: Int, num: Int) {
        print("\nПоставим ")
        if (size < 4)
            print("$num-й ")
        print("$size-палубный...\n")
    }

    fun printError() {
        println("Не корректные координаты")
    }

    // Установка одного корабля. Принимает в качесвте параметра только id корабля
    fun install(id: Int): Pair<Boolean, Boat> {
        val size = id / 10 // Размер корабля - первая цифра id
        val num = id - size * 10 // Номер корабля данного размера - вторая цифра id
        var boat: Boat
        var testCount = 0
        do {

            testCount++
            if (isHuman) printWelcome(size, num) // Сообщение с приглашением поставить корабль
            val readPair = if (isHuman) GetCoord().boatHuman() else GetCoord().boatRobot() // Считываем пару
            val coordBegin = readPair.first // Первый элемент пары - начальная координата корабля
            val isVertical = readPair.second // Второй элемент пары - признак вертикальности
            boat = factory.makeBoat(id, coordBegin, isVertical) // Через фабрику делаем корабль

            if (isHuman && !testBoat(boat))
                printError()
            if (techField.boatList.size > 0 && testCount > 100) {
                println("Ups!!")
                return false to boat
            }
        } while (!testBoat(boat)) // Проверка - может ли корабль с такими координатами корректно стоять на поле.
        // Если проверка пройдена, то ставим его на поле:
        with(techField) {  // На ТехПоле
            boatList[boat.id] = boat // Добавлем в коллекцию кораблей
            update() // Обновляем ТехПоле
            uiInstaller.update() // Обновляем UI поле
            aliveBoatCounter++ // Инкремент счетчика кораблей
        }
        return true to boat
    }

    // Принимает список id кораблей для установки:
    fun installAll(boatsIdToInstall: Collection<Int>) { // Установка всех нужных кораблей:
        for (id in boatsIdToInstall) {
            install(id)
            techField.uiInstaller.print() // Выводим UI поле в консоль
        }
        for (i in 1..50) // Печатаем пустые строки, чтобы "очистить" консоль
            println("\n")
    }

    // Проверка - может ли корабль с такими координатами корректно стоять на поле:
    fun testBoat(boat: Boat): Boolean {
        if (boat.coordEnd.letter > 10 || boat.coordEnd.number > 10) { // Последняя координата не больше 10
            return false
        }

        if (!(techField is TechFieldForAlgoritm) && techField.boatList.size == 0) // Если ставиться первый корабль, то ОК.
            return true

        for (coord in boat.coordinates) {  // Координаты корбля
            for (boatOnField in techField.boatList) { // Уже стоящие корабли
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
            // Сравниваем координаты корабля с координатами сбитых кораблей:
            for (coordScored in techField.scoredList) {
                if (coord == coordScored) {
                    return false
                }
            }
            // Сравниваем координаты корабля с координатами "мимо"
            for (coordFail in techField.failList) {
                if (coord == coordFail) {
                    return false
                }
            }
        }
        return true
    }
}