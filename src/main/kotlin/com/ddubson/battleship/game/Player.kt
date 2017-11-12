package com.ddubson.battleship.game

interface Player {
    fun playerName(): String
    fun oceanGrid(): OceanGrid?
    fun setOceanGrid(oceanGrid: OceanGrid)
    fun targetGrid(): TargetGrid?
    fun setTargetGrid(targetGrid: TargetGrid)
    fun receiveAttack(cell: Cell): CellStatus
}