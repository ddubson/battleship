package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Array2D
import com.ddubson.battleship.game.core.CellAlreadyEngagedException
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus

class StandardTargetGrid : TargetGrid {
    private val size: Int = 8
    private val grid: Array2D<TargetCellStatus>

    init {
        val array = Array(size, { Array(size, { TargetCellStatus.OPEN }) })
        grid = Array2D(size, size, array)
    }

    override fun as2DString(): String {
        var gridVis = "    0 1 2 3 4 5 6 7\n  + - - - - - - - - +\n"

        (0 until grid.xSize).forEach { y ->
            gridVis = gridVis.plus("$y | ")
            (0 until grid.ySize).forEach { x ->
                val symbol = when (grid[x, y]) {
                    TargetCellStatus.HIT -> "*"
                    TargetCellStatus.MISS -> "x"
                    else -> " "
                }
                gridVis = gridVis.plus("$symbol ")
            }
            gridVis = gridVis.plus("|\n")
        }

        gridVis = gridVis.plus("  + - - - - - - - - +").trimIndent()

        return gridVis
    }

    override fun markWithStatus(cell: Cell, targetCellStatus: TargetCellStatus) {
        if (grid[cell.x, cell.y] != TargetCellStatus.OPEN) {
            throw CellAlreadyEngagedException()
        } else
            grid[cell.x, cell.y] = targetCellStatus
    }

    override fun statusOf(cell: Cell): TargetCellStatus = grid[cell.x, cell.y]
}