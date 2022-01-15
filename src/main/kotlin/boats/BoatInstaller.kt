package boats

import coords.CSRBoats
import coords.CoordStringTransformer

class BoatInstaller(private val factory: BoatFactory) {
    fun install(id: Int): Boat {
        val size = id / 10
        val num = id - size * 10
        var boat: Boat
        do {
            print("\nПоставим ")
            if (size < 4)
                print("$num-й ")
            print("$size-палубный...\n")
            val line = CSRBoats().read().toString()
            val coordTransformed = CoordStringTransformer().transform(line)
            val coordBegin = coordTransformed.first
            val isVertical = coordTransformed.second
            boat = factory.makeBoat(id, coordBegin, isVertical)
            if (!factory.testBoat(boat))
                println("Не корректные координаты")
        } while (!factory.testBoat(boat))
        factory.techField.boatList.put(boat.id,boat)
        factory.techField.update()
        factory.techField.uiInstaller.update()
        factory.techField.boatCounter++
        return boat
    }

    fun installAll() {
        val boatsIdToInstall = listOf(41, 31, 32, 21, 22, 23, 11, 12, 13, 14)
        for (id in boatsIdToInstall) {
            this.install(id)
            factory.techField.uiInstaller.print()
        }
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
    }
}