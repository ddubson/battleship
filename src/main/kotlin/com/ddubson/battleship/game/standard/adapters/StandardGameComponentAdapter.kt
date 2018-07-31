package com.ddubson.battleship.game.standard.adapters

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.PlayerBuilder

class StandardGameComponentAdapter(private val CLIAdapter: UserInterfaceAdapter,
                                   private val gridBuilder: GridBuilder,
                                   private val gameBuilder: GameBuilder,
                                   private val playerBuilder: PlayerBuilder) : GameComponentAdapter {
    override fun createPlayerOne(playerName: String, oceanGrid: OceanGrid): Player =
            playerBuilder.newPlayer(playerName, oceanGrid, gridBuilder.newTargetGrid())

    override fun createPlayerTwo(playerName: String, oceanGrid: OceanGrid): Player =
            playerBuilder.newPlayer(playerName, oceanGrid, gridBuilder.newTargetGrid())

    override fun createGame(player1: Player, player2: Player): Game =
            gameBuilder.newGame(player1, player2, CLIAdapter)

    override fun createOceanGrid(): OceanGrid = gridBuilder.newOceanGrid()
}