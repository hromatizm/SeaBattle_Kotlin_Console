package prog

import boats.BoatFactory
import boats.BoatInstaller
import fields.TechField
import turns.Turn
import turns.TurnSequence

// 2 варианта:
// 1 - Человек расставляет, другой человек выбивает
// 2 - Робот расставляет и потом сам выбивает
class OnePlayerMono (isHuman: Boolean){
    val techField = TechField()
    val factory = BoatFactory(techField)
    val installer = BoatInstaller(factory, isHuman)
    val turn = Turn(techField, isHuman)

    fun start() {
        techField.uiInstaller.print()
        installer.installAll(listOf(41, 31, 32, 21, 22, 23, 11, 12, 13, 14))
        TechField.printUI()
        TurnSequence(listOf(turn)).start()
    }
}