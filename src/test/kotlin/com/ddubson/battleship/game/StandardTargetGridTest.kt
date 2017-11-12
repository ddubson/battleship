package com.ddubson.battleship.game

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal class StandardTargetGridTest: Spek({
    given("a standard target grid") {
        val targetGrid = StandardTargetGrid()

        on("marking cell with status") {
            val cell = Cell(1,1)

            on("a hit") {
                it("should report status of 'hit'") {
                    val cellStatus: CellStatus = mock {
                        on { isAHit() } doReturn true
                    }

                    assertFalse(targetGrid.statusOf(cell).isAHit())
                    targetGrid.markWithStatus(cell, cellStatus)
                    assertTrue(targetGrid.statusOf(cell).isAHit())
                }
            }
        }

    }
})