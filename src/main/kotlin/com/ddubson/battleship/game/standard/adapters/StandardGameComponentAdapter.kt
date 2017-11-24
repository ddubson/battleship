package com.ddubson.battleship.game.standard.adapters

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.core.ship.Ship
import com.ddubson.battleship.game.core.builders.ShipBuilder

class StandardGameComponentAdapter(private val uiAdapter: BattleshipGameUiAdapter,
                                   private val shipBuilder: ShipBuilder,
                                   private val gridBuilder: GridBuilder,
                                   private val gameBuilder: GameBuilder,
                                   private val playerBuilder: PlayerBuilder) : GameComponentAdapter {
    override fun createPlayerOne(): Player =
            playerBuilder.newPlayer(uiAdapter.askForPlayerName(), createOceanGrid(),
                    gridBuilder.newTargetGrid())

    override fun createPlayerTwo(): Player =
            playerBuilder.newPlayer(uiAdapter.askForPlayerName(), createOceanGrid(),
                    gridBuilder.newTargetGrid())

    override fun createGame(player1: Player, player2: Player): Game = gameBuilder.newGame(player1, player2, uiAdapter)

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
        uiAdapter.placeShipBanner(ship.type())
        val initialCell = uiAdapter.askForCell(ship)
        val direction = uiAdapter.askForDirection(ship)

        try {
            grid.place(ship, initialCell, direction)
        } catch (ex: ShipOverlapsException) {
            warnOfShipOverlap(grid, ship)
        }

        uiAdapter.displayOceanGrid(grid)
    }

    private fun warnOfShipOverlap(grid: OceanGrid, ship: Ship) {
        uiAdapter.displayWarning("${ship.type()} overlaps another! please choose different coordinates.")
        place(grid, ship)
    }
}