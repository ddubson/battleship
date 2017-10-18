package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.*
import com.ddubson.battleship.game.ship.Ship
import com.ddubson.battleship.game.ship.ShipBuilder

class StandardGameComponentAdapter(private val uiAdapter: BattleshipGameUiAdapter,
                                   private val shipBuilder: ShipBuilder,
                                   private val gridBuilder: GridBuilder,
                                   private val playerArrangementBuilder: PlayerArrangementBuilder,
                                   private val gameBuilder: GameBuilder): GameComponentAdapter {
    override fun createPlayerOne(): Player = Player(uiAdapter.askForPlayerName())

    override fun createPlayerTwo(): Player = Player(uiAdapter.askForPlayerName())

    override fun createPlayerArrangement(player: Player, oceanGrid: OceanGrid, targetGrid: TargetGrid): PlayerArrangement {
        return playerArrangementBuilder.newPlayerArrangement(player, oceanGrid, targetGrid)
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

    override fun createGame(player1Arrangement: PlayerArrangement, player2Arrangement: PlayerArrangement): Game {
        return gameBuilder.newGame(player1Arrangement, player2Arrangement)
    }

    private fun place(grid: OceanGrid, ship: Ship) {
        uiAdapter.placeShipBanner(ship.type())
        val initialCell = uiAdapter.askForCell(ship)
        val direction = uiAdapter.askForDirection(ship)
        grid.place(ship, initialCell, direction)
    }
}