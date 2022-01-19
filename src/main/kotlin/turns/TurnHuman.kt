package turns

import coords.*
import fields.TechField

// Этот наследник читает координату из консоли
class TurnHuman(techField: TechField) : Turn(techField) {

    override fun getCoord(): Coordinate {
        return CSRTurn().read().first
    }
}