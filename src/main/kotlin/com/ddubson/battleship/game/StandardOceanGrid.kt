package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.Ship

class StandardOceanGrid : OceanGrid {
    private val size: Int = 8
    private val ships = mutableMapOf<String, List<Cell>>()
    private var grid = Array2D<Cell>(size, size)

    override fun size(): Int = this.size

    override fun carrierPosition(): List<Cell> = ships["Carrier"].orEmpty()

    override fun battleshipPosition(): List<Cell> = ships["Battleship"].orEmpty()

    override fun cruiserPosition(): List<Cell> = ships["Cruiser"].orEmpty()

    override fun submarinePosition(): List<Cell> = ships["Submarine"].orEmpty()

    override fun destroyerPosition(): List<Cell> = ships["Destroyer"].orEmpty()

    override fun place(ship: Ship, initialCell: Cell, direction: Direction) {
        if (ships.containsKey(ship.type())) {
            throw ShipAlreadyPlacedException()
        }

        val cells = (0 until ship.length()).map {
            if (Direction.VERTICAL == direction) {
                if (isGridCellEmpty(initialCell.x, initialCell.y + it)) {
                    Cell(initialCell.x, initialCell.y + it)
                } else {
                    throw ShipOverlapsException()
                }
            } else {
                if (isGridCellEmpty(initialCell.x + it, initialCell.y)) {
                    Cell(initialCell.x + it, initialCell.y)
                } else {
                    throw ShipOverlapsException()
                }
            }
        }

        cells.forEach { grid[it.x, it.y] = it }

        ships.put(ship.type(), cells)
    }

    private fun isGridCellEmpty(x: Int, y: Int): Boolean = grid[x, y] == null
}