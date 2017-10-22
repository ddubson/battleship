package com.ddubson.battleship.game

class StandardTargetGrid: TargetGrid {
    override fun statusOf(cell: Cell): CellStatus {
        return StandardCellStatus()
    }
}