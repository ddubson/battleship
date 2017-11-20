package com.ddubson.battleship.game

interface PlayerBuilder {
    fun newPlayer(playerName: String, targetGrid: TargetGrid): Player
}