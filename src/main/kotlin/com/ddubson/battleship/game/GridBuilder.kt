package com.ddubson.battleship.game

interface GridBuilder {
    fun newOceanGrid(): OceanGrid
    fun newTargetGrid(): TargetGrid
}