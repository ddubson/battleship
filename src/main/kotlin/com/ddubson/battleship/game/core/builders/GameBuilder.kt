package com.ddubson.battleship.game.core.builders

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter

interface GameBuilder {
    fun newGame(player1: Player, player2: Player, CLIAdapter: UserInterfaceAdapter): Game
}