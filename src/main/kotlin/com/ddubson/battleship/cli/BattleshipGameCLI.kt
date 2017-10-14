package com.ddubson.battleship.cli

import com.ddubson.battleship.game.BattleshipGameUiAdapter

class BattleshipGameCLI : BattleshipGameUiAdapter {
    override fun printBanner() {
        println("--- Welcome to Battleship! ---")
    }
}