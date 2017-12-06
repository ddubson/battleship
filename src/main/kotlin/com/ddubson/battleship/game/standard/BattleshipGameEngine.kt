package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.core.ship.Ship

class BattleshipGameEngine(private val CLIAdapter: BattleshipGameCLIAdapter,
                           private val gameComponentAdapter: GameComponentAdapter,
                           private val shipBuilder: ShipBuilder) {
    fun engage() {
        CLIAdapter.printBanner()

        val oceanGrid1 = gameComponentAdapter.createOceanGrid()
        place(oceanGrid1, shipBuilder.newCarrier())
        place(oceanGrid1, shipBuilder.newCruiser())
        place(oceanGrid1, shipBuilder.newDestroyer())
        place(oceanGrid1, shipBuilder.newSubmarine())
        place(oceanGrid1, shipBuilder.newBattleship())

        val player1 = gameComponentAdapter.createPlayerOne(CLIAdapter.askForPlayerName(), oceanGrid1)
        CLIAdapter.announcePlayer(player1)

        val oceanGrid2 = gameComponentAdapter.createOceanGrid()
        place(oceanGrid2, shipBuilder.newCarrier())
        place(oceanGrid2, shipBuilder.newCruiser())
        place(oceanGrid2, shipBuilder.newDestroyer())
        place(oceanGrid2, shipBuilder.newSubmarine())
        place(oceanGrid2, shipBuilder.newBattleship())
        val player2 = gameComponentAdapter.createPlayerTwo(CLIAdapter.askForPlayerName(), oceanGrid2)
        CLIAdapter.announcePlayer(player2)

        val game = gameComponentAdapter.createGame(player1, player2)
        player1.subscribe(game)
        player2.subscribe(game)

        var attacker = game.nextPlayer()
        CLIAdapter.displayWarning("${attacker.playerName()} goes first.")
        var opponent = game.currentOpponent()

        attacker.attack(opponent)
        CLIAdapter.displayTargetGrid(attacker.targetGrid())

        do {
            attacker = game.nextPlayer()
            CLIAdapter.displayWarning("${attacker.playerName()}, take your turn.")
            opponent = game.currentOpponent()

            attacker.attack(opponent)
            CLIAdapter.displayTargetGrid(attacker.targetGrid())
        } while (opponent.hasShipsLeft())

        CLIAdapter.announceWinner(game.currentAttacker())
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