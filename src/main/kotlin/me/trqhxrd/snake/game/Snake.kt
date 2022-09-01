package me.trqhxrd.snake.game

import me.trqhxrd.snake.gui.Scene
import me.trqhxrd.snake.handler.CollisionHandler
import me.trqhxrd.snake.threading.GameThread
import me.trqhxrd.snake.utils.HighScore
import me.trqhxrd.snake.utils.Locational
import org.apache.logging.log4j.kotlin.Logging
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CopyOnWriteArrayList

class Snake : Logging {
    val head: Head
    val tails: MutableList<Tail>
    val inputs = ArrayBlockingQueue<Direction>(20)
    var pickup: Pickup
        private set
    var direction = Direction.RIGHT
        set(value) {
            if (field.opposite() == value) {
                this.logger.debug("Failed to set direction to $value. [0]")
                return
            }
            this.logger.debug("Set direction from $field to $value.")
            field = value
        }
    var score = 0
        set(value) {
            if (value > highscore) highscore = value
            if (value > field) this.logger.info("Score: $value Highscore: $highscore.")
            field = value
        }
    var highscore = HighScore.load()
    var collisionHandler = CollisionHandler(this)
    var paused = true

    companion object {
        val HEAD_START_LOCATION = Locational(Scene.GRID_WIDTH / 2 + 1, Scene.GRID_HEIGHT / 2)
        const val INIT_SIZE = 2
    }

    init {
        this.logger.debug("Creating new snake.")

        this.head = Head(HEAD_START_LOCATION)
        this.tails = CopyOnWriteArrayList()
        this.pickup = Pickup()

        this.setupTails()

        GameThread(this).start()

        this.logger.debug("Created new snake.")
    }

    private fun setupTails() {
        this.tails.clear()

        // TODO: Dynamic length calculation

        for (i in 1..INIT_SIZE) {
            val tail = Tail(Locational(HEAD_START_LOCATION, -i, 0))
            tail.wait = false
            this.tails.add(tail)
        }
    }

    fun move() {
        if (inputs.isNotEmpty()) this.direction = this.inputs.remove()

        for (i in this.tails.indices.reversed()) {
            val tail = this.tails[i]
            when (i) {
                0 -> tail.move(this.head)
                else -> tail.move(this.tails[i - 1])
            }
        }

        this.head.add(this.direction)
    }

    fun reset() {
        this.head.set(HEAD_START_LOCATION)
        this.direction = Direction.RIGHT
        this.setupTails()
        this.logger.info("You died! Score: $score Highscore: $highscore.")
        this.score = 0
    }

    fun regeneratePickup() {
        this.pickup = Pickup()
    }

    fun addTail() {
        val last =
            if (this.tails.isNotEmpty()) this.tails[this.tails.size - 1]
            else this.head
        this.tails.add(Tail(last.x, last.y))
    }
}