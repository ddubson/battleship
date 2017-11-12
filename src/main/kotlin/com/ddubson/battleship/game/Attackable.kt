package com.ddubson.battleship.game

interface Attackable {
    fun attack(opponent: Player)

    fun receiveAttack(cell: Cell): TargetCellStatus
}