package me.trqhxrd.snake.game

import java.awt.event.KeyEvent.*

enum class Direction(val x: Int, val y: Int, vararg val keyCodes: Int) {
    UP(0, -1, VK_UP, VK_W),
    DOWN(0, 1, VK_DOWN, VK_S),
    LEFT(-1, 0, VK_LEFT, VK_A),
    RIGHT(1, 0, VK_RIGHT, VK_D);

    fun opposite() = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
    }

    companion object {
        fun of(keyCode: Int) = values().firstOrNull { it.keyCodes.contains(keyCode) }
    }
}