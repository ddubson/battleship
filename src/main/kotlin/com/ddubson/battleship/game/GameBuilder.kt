package com.ddubson.battleship.game

interface GameBuilder {
    fun newGame(player1: Player, player2: Player): Game
}