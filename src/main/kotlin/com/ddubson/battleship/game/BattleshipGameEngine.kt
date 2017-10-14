package com.ddubson.battleship.game

class BattleshipGameEngine(private val uiAdapter: BattleshipGameUiAdapter) {
    fun engage() {
        uiAdapter.printBanner()
    }
}