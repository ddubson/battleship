package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.BattleshipGameEngine
import com.ddubson.battleship.game.core.ShipPlacer
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.builders.ShipBuilder

class StandardBattleshipGameEngine(private val CLIAdapter: BattleshipGameCLIAdapter,
                                   private val gameComponentAdapter: GameComponentAdapter,
                                   private val shipBuilder: ShipBuilder,
                                   private val shipPlacer: ShipPlacer) : BattleshipGameEngine {
    override fun engage() {
        CLIAdapter.printBanner()

        val playerName1 = CLIAdapter.askForPlayerName()
        val oceanGrid1 = gameComponentAdapter.createOceanGrid()
        shipPlacer.place(oceanGrid1, shipBuilder.newCarrier())
        CLIAdapter.displayOceanGrid(oceanGrid1)
        shipPlacer.place(oceanGrid1, shipBuilder.newCruiser())
        CLIAdapter.displayOceanGrid(oceanGrid1)
        shipPlacer.place(oceanGrid1, shipBuilder.newDestroyer())
        CLIAdapter.displayOceanGrid(oceanGrid1)
        shipPlacer.place(oceanGrid1, shipBuilder.newSubmarine())
        CLIAdapter.displayOceanGrid(oceanGrid1)
        shipPlacer.place(oceanGrid1, shipBuilder.newBattleship())
        CLIAdapter.displayOceanGrid(oceanGrid1)

        val player1 = gameComponentAdapter.createPlayerOne(playerName1, oceanGrid1)
        CLIAdapter.announcePlayer(player1)

        val playerName2 = CLIAdapter.askForPlayerName()
        val oceanGrid2 = gameComponentAdapter.createOceanGrid()
        shipPlacer.place(oceanGrid2, shipBuilder.newCarrier())
        CLIAdapter.displayOceanGrid(oceanGrid2)
        shipPlacer.place(oceanGrid2, shipBuilder.newCruiser())
        CLIAdapter.displayOceanGrid(oceanGrid2)
        shipPlacer.place(oceanGrid2, shipBuilder.newDestroyer())
        CLIAdapter.displayOceanGrid(oceanGrid2)
        shipPlacer.place(oceanGrid2, shipBuilder.newSubmarine())
        CLIAdapter.displayOceanGrid(oceanGrid2)
        shipPlacer.place(oceanGrid2, shipBuilder.newBattleship())
        CLIAdapter.displayOceanGrid(oceanGrid2)

        val player2 = gameComponentAdapter.createPlayerTwo(playerName2, oceanGrid2)
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
}