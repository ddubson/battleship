package com.ddubson.battleship.game

interface TargetGrid {
    fun statusOf(cell: Cell): CellStatus

    fun markWithStatus(cell: Cell, cellStatus: CellStatus)
}