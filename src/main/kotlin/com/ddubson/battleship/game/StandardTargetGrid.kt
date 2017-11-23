package com.ddubson.battleship.game

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