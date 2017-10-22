package com.ddubson.battleship.game

class StandardCellStatus: CellStatus {
    override fun isAHit(): Boolean {
        return false
    }
}