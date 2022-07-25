package me.trqhxrd.snake.handler

import me.trqhxrd.snake.game.Direction
import me.trqhxrd.snake.game.Snake
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame

class KeyHandler(val snake: Snake) : KeyListener {
    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent) {
        this.snake.direction = Direction.of(e.keyCode)
    }

    override fun keyReleased(e: KeyEvent?) {}
}