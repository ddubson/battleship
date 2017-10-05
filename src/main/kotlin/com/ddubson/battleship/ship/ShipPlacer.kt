package com.ddubson.battleship.ship

import com.ddubson.battleship.Cell
import com.ddubson.battleship.Direction

class ShipPlacer {
    var cellNumber = 0
    lateinit var initialCell: Cell
    var direction: Direction? = null

    fun ship(ship: Ship): ShipPlacer {
        this.cellNumber = ship.length()
        return this
    }

    fun initialCell(initialCell: Cell): ShipPlacer {
        this.initialCell = initialCell
        return this
    }

    fun direction(direction: Direction): ShipPlacer {
        this.direction = direction
        return this
    }

    fun place(): List<Cell> {
        return (0 until this.cellNumber).map { i ->
            if (Direction.VERTICAL == direction) {
                Cell(initialCell.x, initialCell.y + i)
            } else {
                Cell(initialCell.x + i, initialCell.y)
            }
        }
    }
}