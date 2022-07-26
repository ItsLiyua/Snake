package me.trqhxrd.snake.utils

import org.apache.logging.log4j.kotlin.Logging
import org.apache.logging.log4j.kotlin.logger
import kotlin.math.max

class FPS(private val fps: Int = 60):Logging {

    private var previous = 0L

    init {
        this.logger.debug("Running with $fps FPS.")
    }

    fun update() {
        if (this.previous == 0L) {
            this.previous = System.currentTimeMillis()
            return
        }

        val now = System.currentTimeMillis()
        val passed = now - this.previous
        val spf = 1.0 / this.fps * 1000
        Thread.sleep(max((spf - passed).toLong(), 0))
        this.previous = System.currentTimeMillis()
    }
}