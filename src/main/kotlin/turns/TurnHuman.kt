package turns

import coords.*
import fields.TechField

class TurnHuman(techField: TechField) : Turn(techField) {
    override fun getCoord(): CoordInt {
        val line = CSRTurn().read().toString()
        return CoordStringTransformer().transform(line).first
    }
}