package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

class OceanGridTest : Spek({
    given("an ocean grid") {
        var oceanGrid = OceanGrid()

        beforeEachTest {
            oceanGrid = OceanGrid()
        }

        it("should have a size of 8") {
            assertEquals(8, oceanGrid.size)
        }

        it("should fail to place ship twice if already exists in grid") {
            oceanGrid.place(Carrier(), Cell(0, 0), Direction.HORIZONTAL)
            assertThrows(ShipAlreadyPlacedException::class.java, {
                oceanGrid.place(Carrier(), Cell(0, 0), Direction.HORIZONTAL)
            })
        }

        on("place ship horizontally") {
            val direction = Direction.HORIZONTAL
            val initialCell = Cell(0, 0)

            it("should place Carrier") {
                oceanGrid.place(Carrier(), initialCell, direction)
                val carrierPosition: List<Cell> = oceanGrid.carrierPosition()
                val expectedCells = listOf(Cell(0, 0),
                        Cell(1, 0), Cell(2, 0),
                        Cell(3, 0), Cell(4, 0))
                assertEquals(expectedCells, carrierPosition)
            }

            it("should place Battleship") {
                oceanGrid.place(Battleship(), initialCell, direction)
                val battleshipPosition: List<Cell> = oceanGrid.battleshipPosition()
                val expectedCells = listOf(Cell(0, 0),
                        Cell(1, 0), Cell(2, 0), Cell(3, 0))
                assertEquals(expectedCells, battleshipPosition)
            }

            it("should place Cruiser") {
                oceanGrid.place(Cruiser(), initialCell, direction)
                val cruiserPosition: List<Cell> = oceanGrid.cruiserPosition()
                val expectedCells = listOf(Cell(0, 0),
                        Cell(1, 0), Cell(2, 0))
                assertEquals(expectedCells, cruiserPosition)
            }

            it("should place Submarine") {
                oceanGrid.place(Submarine(), initialCell, direction)
                val submarinePosition: List<Cell> = oceanGrid.submarinePosition()
                val expectedCells = listOf(Cell(0, 0), Cell(1, 0), Cell(2, 0))
                assertEquals(expectedCells, submarinePosition)
            }

            it("should place Destroyer") {
                oceanGrid.place(Destroyer(), initialCell, direction)
                val destroyerPosition: List<Cell> = oceanGrid.destroyerPosition()
                val expectedCells = listOf(Cell(0, 0), Cell(1, 0))
                assertEquals(expectedCells, destroyerPosition)
            }
        }

        on("place ship vertically") {
            val direction = Direction.VERTICAL
            val initialCell = Cell(0, 0)

            it("should place Carrier") {
                oceanGrid.place(Carrier(), initialCell, direction)

                val carrierPosition: List<Cell> = oceanGrid.carrierPosition()
                val expectedCells = listOf(Cell(0, 0),
                        Cell(0, 1), Cell(0, 2),
                        Cell(0, 3), Cell(0, 4))
                assertEquals(expectedCells, carrierPosition)
            }

            it("should place Battleship") {
                oceanGrid.place(Battleship(), initialCell, direction)
                val battleshipPosition: List<Cell> = oceanGrid.battleshipPosition()
                val expectedCells = listOf(Cell(0, 0),
                        Cell(0, 1), Cell(0, 2), Cell(0, 3))
                assertEquals(expectedCells, battleshipPosition)
            }

            it("should place Cruiser") {
                oceanGrid.place(Cruiser(), initialCell, direction)
                val cruiserPosition: List<Cell> = oceanGrid.cruiserPosition()
                val expectedCells = listOf(Cell(0, 0),
                        Cell(0, 1), Cell(0, 2))
                assertEquals(expectedCells, cruiserPosition)
            }

            it("should place Submarine") {
                oceanGrid.place(Submarine(), initialCell, direction)
                val submarinePosition: List<Cell> = oceanGrid.submarinePosition()
                val expectedCells = listOf(Cell(0, 0), Cell(0, 1), Cell(0, 2))
                assertEquals(expectedCells, submarinePosition)
            }

            it("should place Destroyer") {
                oceanGrid.place(Destroyer(), initialCell, direction)
                val destroyerPosition: List<Cell> = oceanGrid.destroyerPosition()
                val expectedCells = listOf(Cell(0, 0), Cell(0, 1))
                assertEquals(expectedCells, destroyerPosition)
            }
        }
    }
})