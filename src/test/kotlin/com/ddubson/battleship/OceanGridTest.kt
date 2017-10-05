package com.ddubson.battleship

import com.ddubson.battleship.Direction.HORIZONTAL
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
    fun `place Carrier ship vertically successfully`() {
        val oceanGrid = OceanGrid()
        val carrier = Carrier()

        oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(Cell(0,0))
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

    @Test
    fun `place Carrier ship horizontally successfully`() {
        val oceanGrid = OceanGrid()
        val carrier = Carrier()

        oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(Cell(0,0))
                .direction(HORIZONTAL)
                .place())

        val carrierPosition: List<Cell> = oceanGrid.carrierPosition
        val expectedCells = listOf(Cell(0,0),
                Cell(1,0),
                Cell(2,0),
                Cell(3,0),
                Cell(4,0))
        assertEquals(expectedCells, carrierPosition)
    }
}