package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Array2D
import com.ddubson.battleship.game.core.CellAlreadyEngagedException
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus

class StandardTargetGrid : TargetGrid {
    override fun as2DString(): String {
        TODO()
    }

    private val size: Int = 8
    private val grid: Array2D<TargetCellStatus>

    init {
        val array = Array(size, { Array(size, { TargetCellStatus.OPEN }) })
        grid = Array2D(size, size, array)
    }

    override fun markWithStatus(cell: Cell, targetCellStatus: TargetCellStatus) {
        if (grid[cell.x, cell.y] != TargetCellStatus.OPEN) {
            throw CellAlreadyEngagedException()
        } else
            grid[cell.x, cell.y] = targetCellStatus
    }

    override fun statusOf(cell: Cell): TargetCellStatus = grid[cell.x, cell.y]
}