package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.*
import com.ddubson.battleship.game.core.cell.OceanCellStatus.*
import com.ddubson.battleship.game.core.cell.AttackStatus
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.OceanCellStatus
import com.ddubson.battleship.game.core.ship.Ship

class StandardOceanGrid : OceanGrid {
    private val size: Int = 8
    private val ships = mutableMapOf<String, List<Cell>>()
    private val grid: Array2D<OceanCellStatus>

    init {
        val array = Array(size, { Array(size, { OPEN }) })
        grid = Array2D(size, size, array)
    }

    override fun as2DString(): String {
        var gridVis = "    0 1 2 3 4 5 6 7\n  + - - - - - - - - +\n"

        (0 until grid.xSize).forEach { y ->
            gridVis = gridVis.plus("$y | ")
            (0 until grid.ySize).forEach { x ->
                val symbol = when (grid[x, y]) {
                    OPEN -> " "
                    ENGAGED -> "O"
                    HIT -> "X"
                }
                gridVis = gridVis.plus("$symbol ")
            }
            gridVis = gridVis.plus("|\n")
        }

        gridVis = gridVis.plus("  + - - - - - - - - +").trimIndent()

        return gridVis
    }

    override fun size(): Int = this.size

    override fun carrierPosition(): List<Cell> = ships["Carrier"].orEmpty()

    override fun battleshipPosition(): List<Cell> = ships["Battleship"].orEmpty()

    override fun cruiserPosition(): List<Cell> = ships["Cruiser"].orEmpty()

    override fun submarinePosition(): List<Cell> = ships["Submarine"].orEmpty()

    override fun destroyerPosition(): List<Cell> = ships["Destroyer"].orEmpty()

    override fun statusOf(cell: Cell): OceanCellStatus = this.grid[cell.x, cell.y]

    override fun hasEngagedCells(): Boolean {
        grid.forEach {
            if (it == ENGAGED)
                return true
        }

        return false
    }

    override fun bombard(cell: Cell): AttackStatus {
        val cellStatus = this.grid[cell.x, cell.y]

        return if (cellStatus == ENGAGED) {
            this.grid[cell.x, cell.y] = HIT
            AttackStatus.HIT
        } else {
            AttackStatus.MISS
        }
    }

    override fun place(ship: Ship, initialCell: Cell, direction: Direction) {
        if(initialCell.x < 0 || initialCell.x > grid.xSize-1 ||
                initialCell.y < 0 || initialCell.y > grid.ySize-1) {
            throw ShipBeyondBoundsException()
        }

        if (ships.containsKey(ship.type())) {
            throw ShipAlreadyPlacedException()
        }

        val cells = (0 until ship.length()).map {
            if (Direction.VERTICAL == direction) {
                if (initialCell.y + it > grid.ySize - 1 || initialCell.x > grid.xSize - 1) {
                    throw ShipBeyondBoundsException()
                }

                if (isGridCellEmpty(initialCell.x, initialCell.y + it)) {
                    Cell(initialCell.x, initialCell.y + it)
                } else {
                    throw ShipOverlapsException()
                }
            } else {
                if (initialCell.y > grid.ySize - 1 || initialCell.x + it > grid.xSize - 1) {
                    throw ShipBeyondBoundsException()
                }

                if (isGridCellEmpty(initialCell.x + it, initialCell.y)) {
                    Cell(initialCell.x + it, initialCell.y)
                } else {
                    throw ShipOverlapsException()
                }
            }
        }

        cells.forEach { grid[it.x, it.y] = ENGAGED }

        ships.put(ship.type(), cells)
    }

    private fun isGridCellEmpty(x: Int, y: Int): Boolean = grid[x, y] == OPEN
}