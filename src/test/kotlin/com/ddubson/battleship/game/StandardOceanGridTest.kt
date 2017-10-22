package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

class StandardOceanGridTest : Spek({
    given("a standard ocean grid") {
        on("initial state") {
            val oceanGrid = StandardOceanGrid()

            it("should have a size of 8") {
                assertEquals(8, oceanGrid.size())
            }
        }

        on("trying to place a ship twice") {
            val oceanGrid = StandardOceanGrid()

            it("should fail to place ship twice if already exists in grid") {
                oceanGrid.place(Carrier(), Cell(0, 0), Direction.HORIZONTAL)
                assertThrows(ShipAlreadyPlacedException::class.java, {
                    oceanGrid.place(Carrier(), Cell(0, 0), Direction.HORIZONTAL)
                })
            }
        }

        on("trying to place a ship that overlaps another ship") {
            val oceanGrid = StandardOceanGrid()

            it("should fail to place ship that overlaps another ship") {
                oceanGrid.place(Carrier(), Cell(0, 0), Direction.HORIZONTAL)

                assertThrows(ShipOverlapsException::class.java, {
                    oceanGrid.place(Submarine(), Cell(0, 0), Direction.VERTICAL)
                })
            }
        }

        on("placing ship horizontally") {
            val direction = Direction.HORIZONTAL
            val initialCell = Cell(0, 0)

            it("should place Carrier") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Carrier(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0),
                        Cell(1, 0), Cell(2, 0),
                        Cell(3, 0), Cell(4, 0))
                assertEquals(expectedCells, oceanGrid.carrierPosition())
            }

            it("should place Battleship") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Battleship(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0),
                        Cell(1, 0), Cell(2, 0), Cell(3, 0))
                assertEquals(expectedCells, oceanGrid.battleshipPosition())
            }

            it("should place Cruiser") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Cruiser(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0),
                        Cell(1, 0), Cell(2, 0))
                assertEquals(expectedCells, oceanGrid.cruiserPosition())
            }

            it("should place Submarine") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Submarine(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0), Cell(1, 0), Cell(2, 0))
                assertEquals(expectedCells, oceanGrid.submarinePosition())
            }

            it("should place Destroyer") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Destroyer(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0), Cell(1, 0))
                assertEquals(expectedCells, oceanGrid.destroyerPosition())
            }
        }

        on("placing ship vertically") {
            val direction = Direction.VERTICAL
            val initialCell = Cell(0, 0)

            it("should place Carrier") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Carrier(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0),
                        Cell(0, 1), Cell(0, 2),
                        Cell(0, 3), Cell(0, 4))
                assertEquals(expectedCells, oceanGrid.carrierPosition())
            }

            it("should place Battleship") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Battleship(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0),
                        Cell(0, 1), Cell(0, 2), Cell(0, 3))
                assertEquals(expectedCells, oceanGrid.battleshipPosition())
            }

            it("should place Cruiser") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Cruiser(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0),
                        Cell(0, 1), Cell(0, 2))
                assertEquals(expectedCells, oceanGrid.cruiserPosition())
            }

            it("should place Submarine") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Submarine(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0), Cell(0, 1), Cell(0, 2))
                assertEquals(expectedCells, oceanGrid.submarinePosition())
            }

            it("should place Destroyer") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Destroyer(), initialCell, direction)
                val expectedCells = listOf(Cell(0, 0), Cell(0, 1))
                assertEquals(expectedCells, oceanGrid.destroyerPosition())
            }
        }
    }
})