package com.ddubson.battleship.game

interface Game {
    fun nextPlayer(): Player
    fun currentOpponent(): Player
}