package me.trqhxrd.snake.threading

import me.trqhxrd.snake.game.Snake
import org.apache.logging.log4j.kotlin.Logging

class GameThread(val snake: Snake) : Thread("game"), Logging {

    private var running = true

    init {
        this.logger.debug("Created new GameThread")
    }

    override fun run() {
        while (this.running) {
            if (!this.snake.paused || this.snake.suspendGameLoop) {
                this.snake.move()
                this.snake.collisionHandler.check()
            }
            sleep(200)
        }
    }

    fun shutdownGracefully() {
        this.running = false
    }
}