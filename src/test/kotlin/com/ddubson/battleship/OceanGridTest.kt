package com.ddubson.battleship

import com.ddubson.battleship.Direction.VERTICAL
import com.ddubson.battleship.ship.Carrier
import com.ddubson.battleship.ship.ShipPlacer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OceanGridTest {
    @Test
    fun `ocean grid must return size 8`() {
        val oceanGrid = OceanGrid()
        assertEquals(8, oceanGrid.size)
    }

    @Test
    fun `place Carrier ship successfully`() {
        val oceanGrid = OceanGrid()
        val carrier = Carrier()
        val initialCell = Cell(0,0)

        oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(initialCell)
                .direction(VERTICAL)
                .place())

        val carrierPosition: List<Cell> = oceanGrid.carrierPosition
        val expectedCells = listOf(Cell(0,0),
                Cell(0,1),
                Cell(0,2),
                Cell(0,3),
                Cell(0,4))
        assertEquals(expectedCells, carrierPosition)
    }
}