package com.ddubson.battleship.game

interface GameBuilder {
    fun newGame(player1Arrangement: PlayerArrangement, player2Arrangement: PlayerArrangement): Game
}