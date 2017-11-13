package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.adapters.GameComponentAdapter

class BattleshipGameEngine(private val uiAdapter: BattleshipGameUiAdapter,
                           private val gameComponentAdapter: GameComponentAdapter) {
    fun engage() {
        uiAdapter.printBanner()
        val player1 = gameComponentAdapter.createPlayerOne()
        uiAdapter.announcePlayer(player1)
        val player2 = gameComponentAdapter.createPlayerTwo()
        uiAdapter.announcePlayer(player2)

        val oceanGrid1 = gameComponentAdapter.createOceanGrid(player1)
        gameComponentAdapter.addOceanGridToPlayer(player1, oceanGrid1)
        val targetGrid1 = gameComponentAdapter.createTargetGrid(player1)
        gameComponentAdapter.addTargetGridToPlayer(player1, targetGrid1)

        val oceanGrid2 = gameComponentAdapter.createOceanGrid(player2)
        gameComponentAdapter.addOceanGridToPlayer(player2, oceanGrid2)
        val targetGrid2 = gameComponentAdapter.createTargetGrid(player2)
        gameComponentAdapter.addTargetGridToPlayer(player2, targetGrid2)

        val game = gameComponentAdapter.createGame(player1, player2)

        do {
            val attacker = game.nextPlayer()
            val opponent = game.currentOpponent()

            attacker.attack(opponent)
        } while (opponent.hasShipsLeft())

/*        while(!game.finished()) {
            attacker.attack(opponent)
            opponent.evaluate()
        }

        uiAdapter.announceWinner(game.winner())
        */
    }
}