package me.trqhxrd.snake.gui

import org.apache.logging.log4j.kotlin.Logging
import java.awt.Dimension
import java.awt.Rectangle
import javax.swing.JFrame

class Window : JFrame(TITLE), Logging {

    init {
        this.logger.debug("Creating new window.")

        this.size = Dimension(WIDTH, HEIGHT)
        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.setLocationRelativeTo(null)
        this.layout = null
        this.isResizable = false

        val scene = Scene()
        scene.bounds = Rectangle(0, 0, WIDTH, HEIGHT)
        scene.isVisible = true
        this.add(scene)

        this.requestFocus()
        this.isVisible = true

        this.logger.debug("Created new window.")
    }

    companion object {
        const val WIDTH = 800
        const val HEIGHT = 600
        const val TITLE = "Snake"
    }
}
