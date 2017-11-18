package com.ddubson.battleship.game

interface Game: Subscriber {
    fun nextPlayer(): Player
    fun currentOpponent(): Player
    fun currentAttacker(): Player
}