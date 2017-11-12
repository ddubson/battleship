package com.ddubson.battleship.game

class StandardTargetGrid: TargetGrid {
    private val size: Int = 8
    private val grid: Array2D<CellStatus>

    init {
        val array = Array(size, { Array<CellStatus>(size, { StandardCellStatus() }) })
        grid = Array2D(size, size, array)
    }

    override fun markWithStatus(cell: Cell, cellStatus: CellStatus) {
        grid[cell.x, cell.y] = cellStatus
    }

    override fun statusOf(cell: Cell): CellStatus = grid[cell.x, cell.y]
}