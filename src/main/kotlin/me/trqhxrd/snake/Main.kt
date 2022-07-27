package me.trqhxrd.snake

import me.trqhxrd.snake.game.Snake
import me.trqhxrd.snake.gui.Window
import me.trqhxrd.snake.threading.GameThread
import org.apache.logging.log4j.kotlin.logger

fun main() {
    logger("main").info("Hello World")
    val snake = Snake()
    Window(snake)
}