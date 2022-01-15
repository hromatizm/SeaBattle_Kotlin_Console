package prog

import boats.BoatFactory
import boats.BoatInstaller
import fields.TechField
import turns.Turn
import turns.TurnHuman
import turns.TurnSequence

fun main() {

    val field = TechField()
    val factory = BoatFactory(field)
    val installer = BoatInstaller(factory)
    field.uiInstaller.print()
    installer.installAll()
    field.uiTurns.print()
    val human : Turn = TurnHuman(field)
    val players = listOf(human)
    TurnSequence(players).start()

}