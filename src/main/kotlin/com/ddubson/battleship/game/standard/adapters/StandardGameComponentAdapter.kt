package com.ddubson.battleship.game.standard.adapters

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.core.ship.Ship

class StandardGameComponentAdapter(private val CLIAdapter: BattleshipGameCLIAdapter,
                                   private val shipBuilder: ShipBuilder,
                                   private val gridBuilder: GridBuilder,
                                   private val gameBuilder: GameBuilder,
                                   private val playerBuilder: PlayerBuilder) : GameComponentAdapter {
    override fun createPlayerOne(playerName: String, oceanGrid: OceanGrid): Player =
            playerBuilder.newPlayer(playerName, oceanGrid, gridBuilder.newTargetGrid())

    override fun createPlayerTwo(playerName: String, oceanGrid: OceanGrid): Player =
            playerBuilder.newPlayer(playerName, oceanGrid, gridBuilder.newTargetGrid())

    override fun createGame(player1: Player, player2: Player): Game = gameBuilder.newGame(player1, player2, CLIAdapter)

    override fun createOceanGrid(): OceanGrid {
        val oceanGrid = gridBuilder.newOceanGrid()

        place(oceanGrid, shipBuilder.newCarrier())
        place(oceanGrid, shipBuilder.newCruiser())
        place(oceanGrid, shipBuilder.newDestroyer())
        place(oceanGrid, shipBuilder.newSubmarine())
        place(oceanGrid, shipBuilder.newBattleship())

        return oceanGrid
    }

    private fun place(grid: OceanGrid, ship: Ship) {
        CLIAdapter.placeShipBanner(ship.type())
        val initialCell = CLIAdapter.askForCell(ship)
        val direction = CLIAdapter.askForDirection(ship)

        try {
            grid.place(ship, initialCell, direction)
        } catch (ex: ShipOverlapsException) {
            warnOfShipOverlap(grid, ship)
        }

        CLIAdapter.displayOceanGrid(grid)
    }

    private fun warnOfShipOverlap(grid: OceanGrid, ship: Ship) {
        CLIAdapter.displayWarning("${ship.type()} overlaps another! please choose different coordinates.")
        place(grid, ship)
    }
}