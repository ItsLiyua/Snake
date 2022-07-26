package me.trqhxrd.snake.utils

import me.trqhxrd.snake.game.Direction
import java.awt.Point

open class Location(var x: Int = 0, var y: Int = 0) {

    constructor(other: Location) : this(other.x, other.y)

    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    constructor(point: Point) : this(point.x, point.y)

    fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun set(location: Location) = this.set(location.x, location.y)

    fun add(x: Int, y: Int) = this.set(this.x + x, this.y + y)

    fun add(location: Location) = this.add(location.x, location.y)

    fun add(direction: Direction) = this.add(direction.x, direction.y)

    fun point() = Point(this.x, this.y)

    fun pair() = this.x to this.y

    fun sameLocation(location: Location) = location.x == this.x && location.y == this.y
}