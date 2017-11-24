package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Direction
import com.ddubson.battleship.game.core.ShipAlreadyPlacedException
import com.ddubson.battleship.game.core.ShipBeyondBoundsException
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.cell.AttackStatus
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.OceanCellStatus
import com.ddubson.battleship.game.core.ship.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*

internal class StandardOceanGridTest : Spek({
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

        on("trying to place a ship off the grid") {
            val oceanGrid = StandardOceanGrid()

            it("should throw an exception") {
                assertThrows(ShipBeyondBoundsException::class.java, {
                    oceanGrid.place(Carrier(), Cell(-1, -1), Direction.HORIZONTAL)
                })
            }
        }

        on("trying to place a ship that spills beyond battlespace boundaries") {
            val oceanGrid = StandardOceanGrid()

            it("should throw an exception if the ship spills over vertically") {
                assertThrows(ShipBeyondBoundsException::class.java, {
                    oceanGrid.place(Carrier(), Cell(7, 7), Direction.VERTICAL)
                })
            }

            it("should throw an exception if the ship spills over horizontally") {
                assertThrows(ShipBeyondBoundsException::class.java, {
                    oceanGrid.place(Carrier(), Cell(7, 7), Direction.HORIZONTAL)
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

        on("bombard") {
            it("should return a status of 'miss' when no ships hit.") {
                val oceanGrid = StandardOceanGrid()
                val attackedCell = Cell(0, 0)

                assertEquals(AttackStatus.MISS, oceanGrid.bombard(attackedCell))
                assertEquals(OceanCellStatus.OPEN, oceanGrid.statusOf(attackedCell))
            }

            it("should return a status of 'hit' when ship is hit.") {
                val oceanGrid = StandardOceanGrid()
                val attackedCell = Cell(0, 0)
                oceanGrid.place(Destroyer(), Cell(0, 0), Direction.HORIZONTAL)

                assertEquals(AttackStatus.HIT, oceanGrid.bombard(attackedCell))
                assertEquals(OceanCellStatus.HIT, oceanGrid.statusOf(attackedCell))
            }
        }

        on("identifying if grid has any engaged cells left") {
            it("should return true if there are engaged cells are present") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Carrier(), Cell(0, 0), Direction.HORIZONTAL)
                assertTrue(oceanGrid.hasEngagedCells())
            }

            it("should return false if there are no engaged cells") {
                val oceanGrid = StandardOceanGrid()
                oceanGrid.place(Destroyer(), Cell(0, 1), Direction.HORIZONTAL)
                oceanGrid.bombard(Cell(0, 1))
                oceanGrid.bombard(Cell(1, 1))
                assertFalse(oceanGrid.hasEngagedCells())
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

        on("generating a 2D string representation") {
            val oceanGrid = StandardOceanGrid()
            oceanGrid.place(Carrier(), Cell(0, 0), Direction.VERTICAL)

            val string2d = oceanGrid.as2DString()

            it("should display the string") {
                assertEquals("""
                      0 1 2 3 4 5 6 7
                    + - - - - - - - - +
                  0 | O               |
                  1 | O               |
                  2 | O               |
                  3 | O               |
                  4 | O               |
                  5 |                 |
                  6 |                 |
                  7 |                 |
                    + - - - - - - - - +
                """.trimIndent(), string2d)
            }
        }
    }
})