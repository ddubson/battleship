package com.ddubson.battleship.game

interface Subscriber {
    fun onAttackEvent(): Cell
    fun afterAttackEvent(attacker: Player, opponent: Player, cellStatus: TargetCellStatus)
}