package io.github.fablinmc.fablin

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager

val LOGGER = LogManager.getFormatterLogger("Fablin API")

@Suppress("unused")
fun init() {
    LOGGER.log(Level.INFO, "Loading Fablin API...")
}