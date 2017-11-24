package com.ddubson.battleship.game.core

import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus

interface Subscriber {
    fun onAttackEvent(): Cell
    fun afterAttackEvent(attacker: Player, opponent: Player, cellStatus: TargetCellStatus)
}