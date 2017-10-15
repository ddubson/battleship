package com.ddubson.battleship.cli

import com.ddubson.battleship.game.BattleshipGameUiAdapter
import com.ddubson.battleship.game.Cell
import com.ddubson.battleship.game.Direction
import com.ddubson.battleship.game.Direction.HORIZONTAL
import com.ddubson.battleship.game.Player

class BattleshipGameCLI : BattleshipGameUiAdapter {
    override fun askForDirection(): Direction {
        return HORIZONTAL
    }

    override fun askForCell(): Cell {
        print("Enter initial coordinates (e.g. '1 2' for [1,2]): ")
        val cellInput = readLine()!!.split(" ")
        return Cell(cellInput[0].toInt(), cellInput[1].toInt())
    }

    override fun placeShipBanner(shipType: String) {
        println("Enter $shipType coordinates...")
    }

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