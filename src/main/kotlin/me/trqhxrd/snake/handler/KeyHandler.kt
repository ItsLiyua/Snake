package me.trqhxrd.snake.handler

import me.trqhxrd.snake.game.Direction
import me.trqhxrd.snake.game.Snake
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyHandler(val snake: Snake) : KeyListener {
    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent) {
        val dir = Direction.of(e.keyCode)
        if (dir == Direction.UNDEFINED && e.keyCode == KeyEvent.VK_ESCAPE) this.snake.paused = !this.snake.paused
        else this.snake.inputs.add(dir)
    }

    override fun keyReleased(e: KeyEvent?) {}
}