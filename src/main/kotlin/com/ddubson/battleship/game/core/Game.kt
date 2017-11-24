package com.ddubson.battleship.game.core

interface Game: Subscriber {
    fun nextPlayer(): Player
    fun currentOpponent(): Player
    fun currentAttacker(): Player
}