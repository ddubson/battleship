package com.ddubson.battleship.game

interface Player: Attackable, Subscribable {
    fun playerName(): String
    fun oceanGrid(): OceanGrid?
    fun setOceanGrid(oceanGrid: OceanGrid)
    fun targetGrid(): TargetGrid
    fun hasShipsLeft(): Boolean
}