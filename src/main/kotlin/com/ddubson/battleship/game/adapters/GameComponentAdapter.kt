package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.*

interface GameComponentAdapter {
    fun createOceanGrid(player: Player): OceanGrid
    fun createTargetGrid(player: Player): TargetGrid
    fun createPlayerArrangement(player: Player, oceanGrid: OceanGrid, targetGrid: TargetGrid): PlayerArrangement
    fun createGame(player1Arrangement: PlayerArrangement, player2Arrangement: PlayerArrangement): Game
    fun createPlayerOne(): Player
    fun createPlayerTwo(): Player
}