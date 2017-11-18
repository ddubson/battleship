package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter

interface GameBuilder {
    fun newGame(player1: Player, player2: Player, uiAdapter: BattleshipGameUiAdapter): Game
}