package com.ddubson.battleship.game

class StandardGameBuilder : GameBuilder {
    override fun newGame(player1: Player, player2: Player): Game {
        return StandardGame(player1, player2)
    }
}