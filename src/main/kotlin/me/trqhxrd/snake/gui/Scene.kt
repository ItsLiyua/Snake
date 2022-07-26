package me.trqhxrd.snake.gui

import me.trqhxrd.snake.game.Snake
import me.trqhxrd.snake.utils.FPS
import me.trqhxrd.snake.utils.Locational
import org.apache.logging.log4j.kotlin.Logging
import java.awt.*
import javax.swing.JLabel

class Scene(val snake: Snake) : JLabel(), Logging {

    private var renderIndex = 0L
    private val fps = FPS(60)

    init {
        this.logger.debug("Created new scene.")
    }

    companion object {
        const val X_OFF = Window.WIDTH / 2 - 270
        const val Y_OFF = 20
        const val CELL_SIZE = 32
        const val GRID_WIDTH = 16
        const val GRID_HEIGHT = 16
        const val GRID_WIDTH_PX = GRID_WIDTH * CELL_SIZE
        const val GRID_HEIGHT_PX = GRID_HEIGHT * CELL_SIZE

        val BACKGROUND: Color = Color.LIGHT_GRAY
        val CELL_BORDER: Color = Color.GRAY
        val GRID_BORDER: Color = Color.DARK_GRAY
        val PICKUP: Color = Color.RED
        val TAILS: Color = Color(51, 204, 51)
        val HEAD: Color = Color(0, 153, 0)

        val FONT = Font("Arial", Font.BOLD, 20)

        const val PAUSE = "Game Paused. Press ESC to resume."
        const val PAUSE_X = Window.WIDTH / 2 - 190
        const val PAUSE_Y = Window.HEIGHT / 2 - 20
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        if (g !is Graphics2D) return
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF)

        // Background
        g.color = BACKGROUND
        g.fillRect(0, 0, this.width, this.height)

        // Tails
        for (tail in this.snake.tails) this.fillCell(g, TAILS, tail)

        // Head
        this.fillCell(g, HEAD, this.snake.head)

        // Pickup
        this.fillCell(g, PICKUP, this.snake.pickup)

        // Grid
        g.color = CELL_BORDER
        for (x in 0 until GRID_WIDTH)
            for (y in 0 until GRID_HEIGHT)
                g.drawRect(
                    x * CELL_SIZE + X_OFF,
                    y * CELL_SIZE + Y_OFF,
                    CELL_SIZE,
                    CELL_SIZE
                )

        // Border
        g.color = GRID_BORDER
        g.drawRect(X_OFF, Y_OFF, GRID_WIDTH_PX, GRID_HEIGHT_PX)

        // Score
        g.color = Color.BLACK
        g.font = FONT
        g.drawString("Score: ${this.snake.score}", 5, 25)
        g.drawString("Best: ${this.snake.highscore}", 685, 25)

        // Pause text
        if (this.snake.paused) g.drawString(PAUSE, PAUSE_X, PAUSE_Y)

        this.repaint()

        if (this.renderIndex++ % 10000 == 0L) this.logger.debug("Rendering...")
        this.fps.update()
    }

    private fun fillCell(graphics: Graphics2D, color: Color, x: Int, y: Int) {
        val old = graphics.color!!
        graphics.color = color
        graphics.fillRect(x * CELL_SIZE + X_OFF, y * CELL_SIZE + Y_OFF, CELL_SIZE, CELL_SIZE)
        graphics.color = old
    }

    private fun fillCell(graphics: Graphics2D, color: Color, locational: Locational) =
        this.fillCell(graphics, color, locational.x, locational.y)
}