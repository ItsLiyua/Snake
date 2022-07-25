package me.trqhxrd.snake.game

import me.trqhxrd.snake.gui.Scene
import me.trqhxrd.snake.utils.Locational
import org.apache.logging.log4j.kotlin.Logging

class Pickup(x: Int, y: Int) : Locational(x, y), Logging {
    constructor() : this((0 until Scene.GRID_WIDTH).random(), (0 until Scene.GRID_HEIGHT).random())

    init {
        this.logger.debug("Created new pickup at x=$x,y=$y.")
    }
}