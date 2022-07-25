package me.trqhxrd.snake.game

import me.trqhxrd.snake.gui.Scene
import org.apache.logging.log4j.kotlin.Logging

class Snake : Logging {
    val head: Head
    val tails: MutableList<Tail>
    val pickup: Pickup
    var direction = Direction.RIGHT
        set(value) {
            if (field.opposite() == value) {
                this.logger.debug("Failed to set direction to $value. [0]")
                return
            }
            this.logger.debug("Set direction from $field to $value.")
            field = value
        }

    init {
        this.logger.debug("Creating new snake.")

        this.head = Head(Scene.GRID_WIDTH / 2 + 1, Scene.GRID_HEIGHT / 2)
        this.tails = mutableListOf()
        this.pickup = Pickup()

        for (i in 1..2) this.tails.add(Tail(this.head.x - i, this.head.y))

        this.logger.debug("Created new snake.")
    }

    fun move() {
        if (this.tails.size >= 2) {
            for (index in 1 until this.tails.size) {
                val tail = this.tails.reversed()[index]
                val inFront = this.tails.reversed()[index - 1]
                tail.set(inFront)
            }
        }

        if (tails.size >= 1) {
            this.tails[1].set(this.head)
        }

        this.head.add(this.direction)
    }
}