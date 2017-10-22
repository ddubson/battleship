package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.*
import com.ddubson.battleship.game.ship.Ship
import com.ddubson.battleship.game.ship.ShipBuilder

class StandardGameComponentAdapter(private val uiAdapter: BattleshipGameUiAdapter,
                                   private val shipBuilder: ShipBuilder,
                                   private val gridBuilder: GridBuilder,
                                   private val gameBuilder: GameBuilder,
                                   private val playerBuilder: PlayerBuilder) : GameComponentAdapter {
    override fun addOceanGridToPlayer(player: Player, oceanGrid: OceanGrid) {
        player.setOceanGrid(oceanGrid)
    }

    override fun addTargetGridToPlayer(player: Player, targetGrid: TargetGrid) {
        player.setTargetGrid(targetGrid)
    }

    override fun createPlayerOne(): Player {
        return playerBuilder.newPlayer(uiAdapter.askForPlayerName())
    }

    override fun createPlayerTwo(): Player {
        return playerBuilder.newPlayer(uiAdapter.askForPlayerName())
    }

    override fun createTargetGrid(player: Player): TargetGrid {
        return gridBuilder.newTargetGrid()
    }

    override fun createOceanGrid(player: Player): OceanGrid {
        val oceanGrid = gridBuilder.newOceanGrid()

        place(oceanGrid, shipBuilder.newCarrier())
        place(oceanGrid, shipBuilder.newCruiser())
        place(oceanGrid, shipBuilder.newDestroyer())
        place(oceanGrid, shipBuilder.newSubmarine())
        place(oceanGrid, shipBuilder.newBattleship())

        return oceanGrid
    }

    override fun createGame(player1: Player, player2: Player): Game {
        return gameBuilder.newGame(player1, player2)
    }

    private fun place(grid: OceanGrid, ship: Ship) {
        uiAdapter.placeShipBanner(ship.type())
        val initialCell = uiAdapter.askForCell(ship)
        val direction = uiAdapter.askForDirection(ship)
        grid.place(ship, initialCell, direction)
    }
}