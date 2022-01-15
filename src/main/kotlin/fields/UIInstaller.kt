package fields

class UIInstaller(val techField: TechField) : UIField(techField) {
    override fun update() {
        for (boat in techField.boatList)
            for (coord in boat.value.coords)
                fieldArray[coord.number - 1][coord.letter - 1] = boatChar
    }
}