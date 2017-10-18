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
        val targetGrid1 = gameComponentAdapter.createTargetGrid(player1)
        val player1Arrangement = gameComponentAdapter
                .createPlayerArrangement(player1, oceanGrid1, targetGrid1)

        val oceanGrid2 = gameComponentAdapter.createOceanGrid(player2)
        val targetGrid2 = gameComponentAdapter.createTargetGrid(player2)
        val player2Arrangement = gameComponentAdapter
                .createPlayerArrangement(player2, oceanGrid2, targetGrid2)

        val game = gameComponentAdapter.createGame(player1Arrangement, player2Arrangement)
    }
}