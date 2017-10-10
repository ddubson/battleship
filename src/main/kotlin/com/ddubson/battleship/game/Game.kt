package com.ddubson.battleship.game

class Game(val player1Arrangement: PlayerArrangement,
           val player2Arrangement: PlayerArrangement) {
    fun finished(): Boolean = false
}