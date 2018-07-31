package com.ddubson.battleship.game.standard.builders

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.standard.StandardGame
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder

class StandardGameBuilder : GameBuilder {
    override fun newGame(player1: Player, player2: Player, CLIAdapter: UserInterfaceAdapter): Game =
            StandardGame(player1, player2, CLIAdapter)
}