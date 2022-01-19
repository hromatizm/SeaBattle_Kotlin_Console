package coords

// ЧЕРНОВИК
fun get (): Coordinate {
    val letter = (1..10).random()
    val number = (1..10).random()
    return Coordinate(letter, number)
//    val isVertical: Boolean = Random.nextBoolean()

}