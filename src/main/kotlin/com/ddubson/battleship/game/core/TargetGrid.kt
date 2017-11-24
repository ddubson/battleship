package com.ddubson.battleship.game.core

import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus

interface TargetGrid {
    fun statusOf(cell: Cell): TargetCellStatus

    fun as2DString(): String

    fun markWithStatus(cell: Cell, targetCellStatus: TargetCellStatus)
}