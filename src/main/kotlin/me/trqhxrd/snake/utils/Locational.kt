package me.trqhxrd.snake.utils

import java.awt.Point

abstract class Locational(var x: Int = 0, var y: Int = 0) {

    constructor(other: Locational) : this(other.x, other.y)

    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    constructor(point: Point) : this(point.x, point.y)

    fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun add(x: Int, y: Int) = this.set(this.x + x, this.y + y)

    fun point() = Point(this.x, this.y)

    fun pair() = this.x to this.y
}