package me.trqhxrd.snake.threading

import me.trqhxrd.snake.game.Snake
import org.apache.logging.log4j.kotlin.Logging

class GameThread(val snake: Snake) : Thread("game"), Logging {

    init {
        this.logger.debug("Created new GameThread")
    }

    override fun run() {
        while (true) {
            if (!this.snake.paused) {
                this.snake.move()
                this.snake.collisionHandler.check()
            }
            sleep(200)
        }
    }

}