package prog

import boats.BoatFactory
import boats.BoatInstaller
import fields.TechField
import fields.UIFTurns
import turns.Turn
import turns.TurnSequence

class TwoPlayersGame {
    //Human:
    val techFieldHuman = TechField()
    val factoryHuman = BoatFactory(techFieldHuman)
    val installerHuman = BoatInstaller(factoryHuman, true)


    // Robot:
    val techFieldRobot = TechField()
    val factoryRobot = BoatFactory(techFieldRobot)
    val installerRobot = BoatInstaller(factoryRobot, false)


    // Turns
    val turnHuman = Turn(techFieldRobot,true)
    val turnRobot = Turn(techFieldHuman,false)


    fun start() {
        techFieldHuman.uiTurns.is4Robot = true
        techFieldHuman.uiInstaller.print()
        installerHuman.installAll(listOf(41, 31, 32, 21, 22, 23, 11, 12, 13, 14))
        techFieldRobot.uiInstaller.print()
        installerRobot.installAll(listOf(41, 31, 32, 21, 22, 23, 11, 12, 13, 14))
        techFieldHuman.uiTurns.update()
        TechField.uiScene.reverse()
        TechField.printUI()
        TurnSequence(listOf(turnHuman,turnRobot)).start()
    }
}