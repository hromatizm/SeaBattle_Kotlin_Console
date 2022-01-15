package prog

import boats.BoatFactory
import boats.BoatInstaller
import fields.TechField

fun main() {
//    val one = CoordInt(1,2)
//    val two = CoordInt(1,2)
//    val three = CoordInt(1,3)
//    println(one == two)
//    println(one == three)

    val field = TechField()
    field.print()




    val factory = BoatFactory(field)
    val installer = BoatInstaller(factory)
    installer.installAll()


//    val cSReader = CoordStringReader()
//    println(cSReader.read())
}