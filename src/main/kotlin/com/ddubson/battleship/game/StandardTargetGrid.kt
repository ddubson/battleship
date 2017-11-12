package com.ddubson.battleship.game

class StandardTargetGrid: TargetGrid {
    override fun markWithStatus(cell: Cell, cellStatus: CellStatus) {

    }

    override fun statusOf(cell: Cell): CellStatus {
        return StandardCellStatus()
    }
}