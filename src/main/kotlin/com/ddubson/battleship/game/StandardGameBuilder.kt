package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter

class StandardGameBuilder : GameBuilder {
    override fun newGame(player1: Player, player2: Player, uiAdapter: BattleshipGameUiAdapter): Game =
            StandardGame(player1, player2, uiAdapter)
}