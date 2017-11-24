package com.ddubson.battleship.game.core

import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus

interface Attackable {
    fun attack(opponent: Player)

    fun receiveAttack(cell: Cell): TargetCellStatus
}