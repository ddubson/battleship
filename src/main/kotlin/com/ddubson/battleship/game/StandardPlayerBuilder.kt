package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter

class StandardPlayerBuilder(private val uiAdapter: BattleshipGameUiAdapter) : PlayerBuilder {
    override fun newPlayer(playerName: String): Player {
        return StandardPlayer(playerName, uiAdapter)
    }
}