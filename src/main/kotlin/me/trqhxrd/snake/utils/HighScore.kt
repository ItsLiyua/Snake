package me.trqhxrd.snake.utils

import org.apache.logging.log4j.kotlin.Logging
import java.io.File
import java.io.FileWriter
import java.util.*

object HighScore : Logging {
    private val FILE = File("highscore.dat")

    fun save(highscore: Int) = FileWriter(FILE).use {
        it.write(String(Base64.getEncoder().encode("highscore: $highscore".encodeToByteArray())))
    }


    fun load(): Int {
        return if (!FILE.exists()) {
            this.logger.info("No highscore was found.")
            0
        } else {
            val hs = String(Base64.getDecoder().decode(FILE.readText().encodeToByteArray()))
                .split("highscore: ")[1].toInt()
            this.logger.info("Loaded a highscore of $hs from file.")
            hs
        }
    }
}