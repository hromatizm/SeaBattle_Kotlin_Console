package fields

abstract class UIField(private val techField: TechField) {
    // Значки для отрисовки клеток:
    private val pC = 9810.toChar()
    val blancChar = "$pC" // Пустая клетка
    private val bC = 9899.toChar()
    val boatChar = "[$bC]" // Корабль
    private val dC = 9760.toChar()
    val deadChar = "[$dC]" // Сбитый корабль
    private val fC = 9898.toChar()
    val failChar = "$fC" // Стреляли "мимо"
    val red = "\u001b[31m"
    val blue = "\u001b[34m"

    // Resets previous color codes
    val reset = "\u001b[0m"

    // UI поле 10 на 10 изначально заполенено кодом 1:
    var fieldArray = Array(10, { Array(10, { blancChar }) })

    // Верхняя строка с буквами:
    private val strLetters = arrayOf("    ", "А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К")

    // Обновление содержимого (значков) клеток UI поля (после действия игрока):
    abstract fun update()

    fun clear() {
        fieldArray = Array(10, { Array(10, { blancChar }) })
    }

    // Вывод в консоль UI поля
    fun print() {
        for (item in strLetters) // Верхняя строка с буквами
            print("%-3s".format(item))
        println()
        var i = 1
        for (str in fieldArray) {
            print("%-3s".format(i)) // Левый столбец с цифрами
            for (item in str)
                when (item) {
                    boatChar -> print("%s".format(item)) // Корабли
                    deadChar -> print(red + "%s".format(item) + reset) // Сбитые корабли
                    failChar -> print(" %-2s".format(item)) // Стреляли "мимо"
                    else -> print(blue + " %-2s".format(item) + reset) // Остальное - пустые клетки
                }
            print(" %-3s".format(i)) // Дублируем с права столбец с цифрами
            i++
            println()
        }
        for (item in strLetters) // Дублируем с низу строку с буквами
            print("%-3s".format(item))
        println()
    }
}