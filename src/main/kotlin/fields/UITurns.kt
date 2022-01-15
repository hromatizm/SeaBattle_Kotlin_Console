package fields

class UITurns(val techField: TechField) : UIField(techField) {
    override fun update() {
        for (coord in techField.scoredList)
            fieldArray[coord.number - 1][coord.letter - 1] = deadChar
        for (coord in techField.mimoList)
            if (coord.number > 0 &&
                coord.letter > 0 &&
                coord.number < 11 &&
                coord.letter < 11
            )
                fieldArray[coord.number - 1][coord.letter - 1] = mimoChar
    }
}