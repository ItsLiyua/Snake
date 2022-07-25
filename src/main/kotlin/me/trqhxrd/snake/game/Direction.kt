package me.trqhxrd.snake.game

import java.awt.event.KeyEvent.*

enum class Direction(val x: Int, val y: Int, val keyCode: Int) {
    UP(0, -1, VK_UP),
    DOWN(0, 1, VK_DOWN),
    LEFT(-1, 0, VK_LEFT),
    RIGHT(1, 0, VK_RIGHT),
    UNDEFINED(0, 0, -1);

    fun opposite() = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
        else -> UNDEFINED
    }

    companion object {
        fun of(keyCode: Int) = values().firstOrNull { it.keyCode == keyCode } ?: UNDEFINED
    }
}