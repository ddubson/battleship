package com.ddubson.battleship

import com.ddubson.battleship.game.core.BattleshipGameEngine
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BattleshipGameCLIApplication(private val gameEngine: BattleshipGameEngine) : CommandLineRunner {
    override fun run(vararg args: String?) = gameEngine.engage()
}

fun main(args: Array<String>) {
    SpringApplication.run(BattleshipGameCLIApplication::class.java, *args)
}