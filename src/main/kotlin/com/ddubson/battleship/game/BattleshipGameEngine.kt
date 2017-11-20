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

        val game = gameComponentAdapter.createGame(player1, player2)
        player1.subscribe(game)
        player2.subscribe(game)

        var attacker = game.nextPlayer()
        uiAdapter.displayWarning("${attacker.playerName()} goes first.")
        var opponent = game.currentOpponent()

        attacker.attack(opponent)

        do {
            attacker = game.nextPlayer()
            uiAdapter.displayWarning("${attacker.playerName()}, take your turn.")
            opponent = game.currentOpponent()

            attacker.attack(opponent)
        } while (opponent.hasShipsLeft())

        uiAdapter.announceWinner(game.currentAttacker())
    }
}