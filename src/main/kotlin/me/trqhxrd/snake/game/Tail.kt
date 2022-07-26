package me.trqhxrd.snake.game

import me.trqhxrd.snake.utils.Locational
import org.apache.logging.log4j.kotlin.Logging

class Tail(x: Int, y: Int) : Locational(x, y), Logging {

    var wait = true

    constructor(loc: Locational) : this(loc.x, loc.y)

    init {
        this.logger.debug("Created new tail at x=$x,y=$y.")
    }

    fun move(target: Locational) {
        if (!this.wait) this.set(target)
        else this.wait = false
    }

    override fun toString(): String {
        return "Tail(wait=$wait)"
    }
}