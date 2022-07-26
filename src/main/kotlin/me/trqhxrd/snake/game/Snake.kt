package me.trqhxrd.snake.game

import me.trqhxrd.snake.gui.Scene
import me.trqhxrd.snake.handler.CollisionHandler
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
            if (value == Direction.UNDEFINED) return
            if (this.waitToMove) return
            if (field.opposite() == value) {
                this.logger.debug("Failed to set direction to $value. [0]")
                return
            }
            this.logger.debug("Set direction from $field to $value.")
            field = value
            this.waitToMove = true
        }
    var score = 0
        set(value) {
            if (value > highscore) highscore = value
            if (value > field) this.logger.info("Score: $value Highscore: $highscore.")
            field = value
        }
    var highscore = HighScore.load()
    var collisionHandler = CollisionHandler(this)
    var waitToMove = false
    var paused = false
    var suspendGameLoop = false

    companion object {
        val CENTER = Locational(Scene.GRID_WIDTH / 2, Scene.GRID_HEIGHT / 2)
    }

    init {
        this.suspendGameLoop = true
        this.logger.debug("Creating new snake.")

        this.head = Head(Locational(CENTER, 1, 0))
        this.tails = CopyOnWriteArrayList()
        this.pickup = Pickup()

        this.setupTails(CENTER)

        this.suspendGameLoop = false
        this.logger.debug("Created new snake.")
    }

    fun move() {
        if (inputs.isNotEmpty()) this.direction = this.inputs.remove()

        if (this.tails.size >= 2) {
            this.tails
                .subList(1, this.tails.size)
                .reversed()
                .forEachIndexed { index, tail -> tail.move(this.tails.reversed()[index + 1]) }
        }

        if (tails.size >= 1) this.tails[0].move(this.head)

        this.head.add(this.direction)
        this.waitToMove = false
    }

    fun reset() {
        this.suspendGameLoop = true
        this.head.set(Locational(CENTER, 1, 0))
        this.tails.clear()
        this.setupTails(CENTER)
        this.logger.info("You died! Score: $score Highscore: $highscore.")
        this.score = 0
        this.direction = Direction.RIGHT
        this.suspendGameLoop = false
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

    private fun setupTails(center: Locational) {
        for (i in 0..1) {
            val tail = Tail(Locational(center, -i, 0))
            this.tails.add(tail)
        }
    }
}