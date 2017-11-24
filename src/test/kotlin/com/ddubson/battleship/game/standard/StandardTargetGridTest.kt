package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.CellAlreadyEngagedException
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

internal class StandardTargetGridTest : Spek({
    given("a standard target grid") {
        on("marking cell with status") {
            val targetGrid = StandardTargetGrid()
            it("should report 'open' by default") {
                val cell = Cell(0, 0)
                assertEquals(TargetCellStatus.OPEN, targetGrid.statusOf(cell))
            }

            it("should report status of 'hit'") {
                val cell = Cell(1, 1)
                val targetCellStatus: TargetCellStatus = TargetCellStatus.HIT

                assertEquals(TargetCellStatus.OPEN, targetGrid.statusOf(cell))
                targetGrid.markWithStatus(cell, targetCellStatus)
                assertEquals(TargetCellStatus.HIT, targetGrid.statusOf(cell))
            }

            it("should report status of 'miss'") {
                val cell = Cell(1, 2)
                val targetCellStatus: TargetCellStatus = TargetCellStatus.MISS

                assertEquals(TargetCellStatus.OPEN, targetGrid.statusOf(cell))
                targetGrid.markWithStatus(cell, targetCellStatus)
                assertEquals(TargetCellStatus.MISS, targetGrid.statusOf(cell))
            }

            it("should not be able to update status of cell if already 'hit' or 'miss'") {
                val cell = Cell(4, 4)
                targetGrid.markWithStatus(cell, TargetCellStatus.MISS)

                assertEquals(TargetCellStatus.MISS, targetGrid.statusOf(cell))
                assertThrows(CellAlreadyEngagedException::class.java,
                        { targetGrid.markWithStatus(cell, TargetCellStatus.HIT) })

                val anotherCell = Cell(2, 3)
                targetGrid.markWithStatus(anotherCell, TargetCellStatus.HIT)
                assertThrows(CellAlreadyEngagedException::class.java,
                        { targetGrid.markWithStatus(cell, TargetCellStatus.MISS) })
            }
        }

        on("print as 2D string") {
            val targetGrid = StandardTargetGrid()
            targetGrid.markWithStatus(Cell(0, 0), TargetCellStatus.HIT)
            targetGrid.markWithStatus(Cell(1, 1), TargetCellStatus.MISS)
            targetGrid.markWithStatus(Cell(2, 2), TargetCellStatus.HIT)
            targetGrid.markWithStatus(Cell(3, 3), TargetCellStatus.MISS)
            targetGrid.markWithStatus(Cell(4, 4), TargetCellStatus.HIT)
            targetGrid.markWithStatus(Cell(5, 5), TargetCellStatus.MISS)
            targetGrid.markWithStatus(Cell(6, 6), TargetCellStatus.HIT)
            targetGrid.markWithStatus(Cell(7, 7), TargetCellStatus.MISS)
            val string2d = targetGrid.as2DString()

            it("should print the string") {
                assertEquals("""
                      0 1 2 3 4 5 6 7
                    + - - - - - - - - +
                  0 | *               |
                  1 |   x             |
                  2 |     *           |
                  3 |       x         |
                  4 |         *       |
                  5 |           x     |
                  6 |             *   |
                  7 |               x |
                    + - - - - - - - - +
                """.trimIndent(), string2d)
            }
        }
    }
})