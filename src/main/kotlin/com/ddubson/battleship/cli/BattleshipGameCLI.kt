package com.ddubson.battleship.cli

import com.ddubson.battleship.game.BattleshipGameUiAdapter
import com.ddubson.battleship.game.Player

class BattleshipGameCLI : BattleshipGameUiAdapter {
    override fun announcePlayer(player: Player) {
        println("Player ${player.playerName} has entered the battlespace!")
    }

    override fun printBanner() {
        println("--- Welcome to Battleship! ---")
    }

    override fun createPlayerOne(): Player {
        print("Enter player one name: ")
        val playerName: String? = readLine()

        return Player(playerName!!)
    }

    override fun createPlayerTwo(): Player {
        print("Enter player two name: ")
        val playerName: String? = readLine()

        return Player(playerName!!)
    }
}