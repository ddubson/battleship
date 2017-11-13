package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.Ship

interface OceanGrid {
    fun size(): Int

    fun carrierPosition(): List<Cell>

    fun battleshipPosition(): List<Cell>

    fun cruiserPosition(): List<Cell>

    fun submarinePosition(): List<Cell>

    fun destroyerPosition(): List<Cell>

    fun place(ship: Ship, initialCell: Cell, direction: Direction)

    fun bombard(cell: Cell): AttackStatus

    fun statusOf(cell: Cell): OceanCellStatus

    fun hasEngagedCells(): Boolean
}