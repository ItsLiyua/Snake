package me.trqhxrd.snake.handler

import me.trqhxrd.snake.game.Snake
import me.trqhxrd.snake.utils.HighScore
import org.apache.logging.log4j.kotlin.Logging
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import kotlin.system.exitProcess

class WindowHandler(val snake: Snake) : WindowListener, Logging {
    override fun windowOpened(p0: WindowEvent?) {}

    override fun windowClosing(p0: WindowEvent?) {
        HighScore.save(this.snake.highscore)
        this.logger.info("Goodbye!")
        exitProcess(0)
    }

    override fun windowClosed(p0: WindowEvent?) {
    }

    override fun windowIconified(p0: WindowEvent?) {}

    override fun windowDeiconified(p0: WindowEvent?) {}

    override fun windowActivated(p0: WindowEvent?) {}

    override fun windowDeactivated(p0: WindowEvent?) {}
}