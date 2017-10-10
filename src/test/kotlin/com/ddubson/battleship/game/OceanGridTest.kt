package com.ddubson.battleship.game

import com.ddubson.battleship.game.Direction.HORIZONTAL
import com.ddubson.battleship.game.Direction.VERTICAL
import com.ddubson.battleship.game.ship.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OceanGridTest {
    private lateinit var oceanGrid: OceanGrid
    private lateinit var carrier: Carrier
    private lateinit var battleship: Battleship
    private lateinit var cruiser: Cruiser
    private lateinit var submarine: Submarine
    private lateinit var destroyer: Destroyer

    @BeforeEach
    fun before() {
        oceanGrid = OceanGrid()
        carrier = Carrier()
        battleship = Battleship()
        cruiser = Cruiser()
        submarine = Submarine()
        destroyer = Destroyer()
    }

    @Test
    fun `ocean grid must return size 8`() {
        assertEquals(8, oceanGrid.size)
    }

    @Test
    fun `place Carrier vertically successfully`() {
        oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(Cell(0, 0))
                .direction(VERTICAL)
                .place())

        val carrierPosition: List<Cell> = oceanGrid.carrierPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(0, 1),
                Cell(0, 2),
                Cell(0, 3),
                Cell(0, 4))
        assertEquals(expectedCells, carrierPosition)
    }

    @Test
    fun `place Carrier horizontally successfully`() {
        oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(Cell(0, 0))
                .direction(HORIZONTAL)
                .place())

        val carrierPosition: List<Cell> = oceanGrid.carrierPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(1, 0),
                Cell(2, 0),
                Cell(3, 0),
                Cell(4, 0))
        assertEquals(expectedCells, carrierPosition)
    }

    @Test
    fun `place Battleship horizontally successfully`() {
        oceanGrid.placeBattleship(ShipPlacer()
                .ship(battleship)
                .initialCell(Cell(0, 0))
                .direction(HORIZONTAL)
                .place())

        val battleshipPosition: List<Cell> = oceanGrid.battleshipPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(1, 0),
                Cell(2, 0),
                Cell(3, 0))
        assertEquals(expectedCells, battleshipPosition)
    }

    @Test
    fun `place Battleship vertically successfully`() {
        oceanGrid.placeBattleship(ShipPlacer()
                .ship(battleship)
                .initialCell(Cell(0, 0))
                .direction(VERTICAL)
                .place())

        val battleshipPosition: List<Cell> = oceanGrid.battleshipPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(0, 1),
                Cell(0, 2),
                Cell(0, 3))
        assertEquals(expectedCells, battleshipPosition)
    }

    @Test
    fun `place Cruiser horizontally successfully`() {
        oceanGrid.placeCruiser(ShipPlacer()
                .ship(cruiser)
                .initialCell(Cell(0, 0))
                .direction(HORIZONTAL)
                .place())

        val cruiserPosition: List<Cell> = oceanGrid.cruiserPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(1, 0),
                Cell(2, 0))
        assertEquals(expectedCells, cruiserPosition)
    }

    @Test
    fun `place Cruiser vertically successfully`() {
        oceanGrid.placeCruiser(ShipPlacer()
                .ship(cruiser)
                .initialCell(Cell(0, 0))
                .direction(VERTICAL)
                .place())

        val cruiserPosition: List<Cell> = oceanGrid.cruiserPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(0, 1),
                Cell(0, 2))
        assertEquals(expectedCells, cruiserPosition)
    }

    @Test
    fun `place Submarine horizontally successfully`() {
        oceanGrid.placeSubmarine(ShipPlacer()
                .ship(submarine)
                .initialCell(Cell(0, 0))
                .direction(HORIZONTAL)
                .place())

        val submarinePosition: List<Cell> = oceanGrid.submarinePosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(1, 0),
                Cell(2, 0))
        assertEquals(expectedCells, submarinePosition)
    }

    @Test
    fun `place Submarine vertically successfully`() {
        oceanGrid.placeSubmarine(ShipPlacer()
                .ship(submarine)
                .initialCell(Cell(0, 0))
                .direction(VERTICAL)
                .place())

        val submarinePosition: List<Cell> = oceanGrid.submarinePosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(0, 1),
                Cell(0, 2))
        assertEquals(expectedCells, submarinePosition)
    }

    @Test
    fun `place Destroyer horizontally successfully`() {
        oceanGrid.placeDestroyer(ShipPlacer()
                .ship(destroyer)
                .initialCell(Cell(0, 0))
                .direction(HORIZONTAL)
                .place())

        val destroyerPosition: List<Cell> = oceanGrid.destroyerPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(1, 0))
        assertEquals(expectedCells, destroyerPosition)
    }

    @Test
    fun `place Destroyer vertically successfully`() {
        oceanGrid.placeDestroyer(ShipPlacer()
                .ship(destroyer)
                .initialCell(Cell(0, 0))
                .direction(VERTICAL)
                .place())

        val destroyerPosition: List<Cell> = oceanGrid.destroyerPosition
        val expectedCells = listOf(Cell(0, 0),
                Cell(0, 1))
        assertEquals(expectedCells, destroyerPosition)
    }
}