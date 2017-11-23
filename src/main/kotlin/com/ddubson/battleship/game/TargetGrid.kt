package com.ddubson.battleship.game

interface TargetGrid {
    fun statusOf(cell: Cell): TargetCellStatus

    fun as2DString(): String

    fun markWithStatus(cell: Cell, targetCellStatus: TargetCellStatus)
}