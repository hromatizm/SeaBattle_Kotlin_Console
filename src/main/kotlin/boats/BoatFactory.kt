package boats

import coords.CoordInt
import fields.TechField

class BoatFactory(val techField: TechField) {

    fun makeBoat(id: Int, coordBegin: CoordInt, isVertical: Boolean): Boat {
        val boatDraft = makeBoatDraft(id, coordBegin, isVertical)
        boatDraft.frame = makeFrame(boatDraft)
        return boatDraft
    }

    private fun makeBoatDraft(id: Int, coordBegin: CoordInt, isVertical: Boolean): Boat {
        return Boat(id, coordBegin, isVertical)
    }

    private fun makeFrame(boat: Boat): BoatFrame {
        val frame = BoatFrame()
        val frameSize = boat.size + 2

        if (boat.isVertical) {
            val frameTopNumber = boat.coordBegin.number - 1
            frame.coords.add(CoordInt(boat.coordBegin.letter, frameTopNumber))
            frame.coords.add(CoordInt(boat.coordEnd.letter, frameTopNumber + frameSize - 1))
            for (number in 0 until frameSize) {
                frame.coords.add(CoordInt(boat.coordBegin.letter - 1, frameTopNumber + number))
                frame.coords.add(CoordInt(boat.coordBegin.letter + 1, frameTopNumber + number))
            }
        } else {
            val frameLeftLetter = boat.coordBegin.letter - 1
            frame.coords.add(CoordInt(frameLeftLetter, boat.coordBegin.number))
            frame.coords.add(CoordInt(frameLeftLetter + frameSize - 1, boat.coordEnd.number))
            for (number in 0 until frameSize) {
                frame.coords.add(CoordInt(frameLeftLetter + number, boat.coordBegin.number - 1))
                frame.coords.add(CoordInt(frameLeftLetter + number, boat.coordBegin.number + 1))
            }
        }
        return frame
    }

    fun testBoat(boat: Boat): Boolean {
        if (boat.coordEnd.letter > 10 || boat.coordEnd.number > 10) {
            return false
        }
        if (techField.boatList.size == 0)
            return true
        for (coord in boat.coords) {
            for (boatOnField in techField.boatList) {
                for (coordBF in boatOnField.coords) {
                    if (coord == coordBF)
                    return false

                }
                for (coordFrame in boatOnField.frame.coords) {
                    if (coord == coordFrame)
                    return false
                }
            }
        }
        return true
    }
}