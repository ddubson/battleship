package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.Game
import com.ddubson.battleship.game.OceanGrid
import com.ddubson.battleship.game.Player
import com.ddubson.battleship.game.TargetGrid

interface GameComponentAdapter {
    fun createOceanGrid(player: Player): OceanGrid
    fun createTargetGrid(player: Player): TargetGrid
    fun createGame(player1: Player, player2: Player): Game
    fun createPlayerOne(): Player
    fun createPlayerTwo(): Player
    fun addOceanGridToPlayer(player: Player, oceanGrid: OceanGrid)
    fun addTargetGridToPlayer(player: Player, targetGrid: TargetGrid)
}