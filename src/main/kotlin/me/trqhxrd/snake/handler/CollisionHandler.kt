package me.trqhxrd.snake.handler

import me.trqhxrd.snake.game.Snake
import me.trqhxrd.snake.gui.Scene
import org.apache.logging.log4j.kotlin.Logging

class CollisionHandler(val snake: Snake) : Logging {

    init {
        this.logger.debug("Created new CollisionHandler.")
    }

    fun check() {
        this.checkTail()
        this.checkWall()
        this.checkPickup()
    }

    fun checkWall() {
        if (
            snake.head.x <= 0 ||
            snake.head.x >= Scene.GRID_WIDTH ||
            snake.head.y <= 0 ||
            snake.head.y >= Scene.GRID_HEIGHT
        ) snake.reset()
    }

    fun checkTail() {
        for (tail in snake.tails) {
            if (tail.sameLocation(snake.head)) {
                snake.reset()
                break
            }
        }
    }

    fun checkPickup() {
        if (this.snake.head.sameLocation(this.snake.pickup)) {
            this.snake.regeneratePickup()
            this.snake.score += 1
            this.snake.addTail()
        }
    }
}