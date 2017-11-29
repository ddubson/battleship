package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter

class BattleshipGameEngine(private val CLIAdapter: BattleshipGameCLIAdapter,
                           private val gameComponentAdapter: GameComponentAdapter) {
    fun engage() {
        CLIAdapter.printBanner()
        val player1 = gameComponentAdapter.createPlayerOne()
        CLIAdapter.announcePlayer(player1)
        val player2 = gameComponentAdapter.createPlayerTwo()
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