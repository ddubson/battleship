package com.ddubson.battleship.game

class StandardGameBuilder : GameBuilder {
    override fun newGame(player1Arrangement: PlayerArrangement, player2Arrangement: PlayerArrangement): Game {
        return StandardGame(player1Arrangement, player2Arrangement)
    }
}