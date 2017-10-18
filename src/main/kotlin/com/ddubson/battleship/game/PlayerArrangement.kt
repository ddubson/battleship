package com.ddubson.battleship.game

interface PlayerArrangement {
    fun player(): Player

    fun oceanGrid(): OceanGrid

    fun targetGrid(): TargetGrid
}