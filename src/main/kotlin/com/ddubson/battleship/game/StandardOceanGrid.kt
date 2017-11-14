package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.Ship

class StandardOceanGrid : OceanGrid {
    private val size: Int = 8

    private val ships = mutableMapOf<String, List<Cell>>()
    private val grid: Array2D<OceanCellStatus>

    init {
        val array = Array(size, { Array(size, { OceanCellStatus.OPEN }) })
        grid = Array2D(size, size, array)
    }

    override fun as2DString(): String {
        var gridVis = "    0 1 2 3 4 5 6 7\n  + - - - - - - - - +\n"

        (0 until grid.xSize).forEach { y ->
            gridVis = gridVis.plus("$y | ")
            (0 until grid.ySize).forEach { x ->
                val symbol = when(grid[x,y]) {
                    OceanCellStatus.OPEN -> " "
                    OceanCellStatus.ENGAGED -> "O"
                    OceanCellStatus.HIT -> "X"
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
            if (it == OceanCellStatus.ENGAGED)
                return true
        }

        return false
    }

    override fun bombard(cell: Cell): AttackStatus {
        val cellStatus = this.grid[cell.x, cell.y]

        return if (cellStatus == OceanCellStatus.ENGAGED) {
            this.grid[cell.x, cell.y] = OceanCellStatus.HIT
            AttackStatus.HIT
        } else {
            AttackStatus.MISS
        }
    }

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

        cells.forEach { grid[it.x, it.y] = OceanCellStatus.ENGAGED }

        ships.put(ship.type(), cells)
    }

    private fun isGridCellEmpty(x: Int, y: Int): Boolean = grid[x, y] == OceanCellStatus.OPEN
}