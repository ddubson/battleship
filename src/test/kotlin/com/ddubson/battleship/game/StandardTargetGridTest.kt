package com.ddubson.battleship.game

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

internal class StandardTargetGridTest : Spek({
    given("a standard target grid") {
        val targetGrid = StandardTargetGrid()

        on("marking cell with status") {
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

                val anotherCell = Cell(2,3)
                targetGrid.markWithStatus(anotherCell, TargetCellStatus.HIT)
                assertThrows(CellAlreadyEngagedException::class.java,
                        { targetGrid.markWithStatus(cell, TargetCellStatus.MISS) })
            }
        }
    }
})