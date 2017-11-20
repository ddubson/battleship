package com.ddubson.battleship.game

interface PlayerBuilder {
    fun newPlayer(playerName: String,
                  oceanGrid: OceanGrid,
                  targetGrid: TargetGrid): Player
}