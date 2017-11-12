package com.ddubson.battleship.game

interface TargetGrid {
    fun statusOf(cell: Cell): TargetCellStatus

    fun markWithStatus(cell: Cell, targetCellStatus: TargetCellStatus)
}