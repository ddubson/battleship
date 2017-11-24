package com.ddubson.battleship.game.core.adapters

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player

interface GameComponentAdapter {
    fun createOceanGrid(): OceanGrid
    fun createGame(player1: Player, player2: Player): Game
    fun createPlayerOne(): Player
    fun createPlayerTwo(): Player
}