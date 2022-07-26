package me.trqhxrd.snake.handler

import me.trqhxrd.snake.game.Direction
import me.trqhxrd.snake.game.Snake
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyHandler(val snake: Snake) : KeyListener {
    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent) = this.snake.inputs.add(Direction.of(e.keyCode)).let { }

    override fun keyReleased(e: KeyEvent?) {}
}