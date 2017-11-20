package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.Game
import com.ddubson.battleship.game.OceanGrid
import com.ddubson.battleship.game.Player
import com.ddubson.battleship.game.TargetGrid

interface GameComponentAdapter {
    fun createOceanGrid(): OceanGrid
    fun createTargetGrid(): TargetGrid
    fun createGame(player1: Player, player2: Player): Game
    fun createPlayerOne(): Player
    fun createPlayerTwo(): Player
}