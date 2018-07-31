package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.BattleshipGameEngine
import com.ddubson.battleship.game.core.ShipPlacer
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter
import com.ddubson.battleship.game.core.builders.ShipBuilder

class StandardBattleshipGameEngine(private val userInterfaceAdapter: UserInterfaceAdapter,
                                   private val gameComponentAdapter: GameComponentAdapter,
                                   private val shipBuilder: ShipBuilder,
                                   private val shipPlacer: ShipPlacer) : BattleshipGameEngine {
    override fun engage() {
        userInterfaceAdapter.printBanner()

        val players = (1..2).map {
            val playerName = userInterfaceAdapter.askForPlayerName()
            val oceanGrid = gameComponentAdapter.createOceanGrid()
            shipPlacer.place(oceanGrid, shipBuilder.newCarrier())
            userInterfaceAdapter.displayOceanGrid(oceanGrid)
            shipPlacer.place(oceanGrid, shipBuilder.newCruiser())
            userInterfaceAdapter.displayOceanGrid(oceanGrid)
            shipPlacer.place(oceanGrid, shipBuilder.newDestroyer())
            userInterfaceAdapter.displayOceanGrid(oceanGrid)
            shipPlacer.place(oceanGrid, shipBuilder.newSubmarine())
            userInterfaceAdapter.displayOceanGrid(oceanGrid)
            shipPlacer.place(oceanGrid, shipBuilder.newBattleship())
            userInterfaceAdapter.displayOceanGrid(oceanGrid)


            val player = gameComponentAdapter.createPlayerOne(playerName, oceanGrid)
            userInterfaceAdapter.announcePlayer(player)
            player
        }

        val (player1, player2) = players
        val game = gameComponentAdapter.createGame(player1, player2)
        player1.subscribe(game)
        player2.subscribe(game)

        var attacker = game.nextPlayer()
        userInterfaceAdapter.displayWarning("${attacker.playerName()} goes first.")
        var opponent = game.currentOpponent()

        attacker.attack(opponent)
        userInterfaceAdapter.displayTargetGrid(attacker.targetGrid())

        do {
            attacker = game.nextPlayer()
            userInterfaceAdapter.displayWarning("${attacker.playerName()}, take your turn.")
            opponent = game.currentOpponent()

            attacker.attack(opponent)
            userInterfaceAdapter.displayTargetGrid(attacker.targetGrid())
        } while (opponent.hasShipsLeft())

        userInterfaceAdapter.announceWinner(game.currentAttacker())
    }
}