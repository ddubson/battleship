package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals

internal class StandardPlayerSpec : Spek({
    given("a player") {
        on("returning player name") {
            val player = StandardPlayer("Player 1", mock {})
            it("should return the player's name") {
                assertEquals("Player 1", player.playerName())
            }
        }

        on("taking a turn") {
            val attackedCell = Cell(0, 1)
            val uiAdapter: BattleshipGameUiAdapter = mock {
                on { askForAttackCell() } doReturn attackedCell
            }
            val player = StandardPlayer("A Player", uiAdapter)
            val cellStatus = StandardCellStatus()
            val targetGrid: TargetGrid = mock {}
            val opponent: Player = mock {
                on { receiveAttack(attackedCell) } doReturn cellStatus
            }

            player.setOceanGrid(StandardOceanGrid())
            player.setTargetGrid(targetGrid)
            player.takeTurn(opponent)

            it("should ask the player to enter the cell to hit") {
                verify(uiAdapter).askForAttackCell()
            }

            it("should update the ocean grid of the opponent") {
                verify(opponent).receiveAttack(attackedCell)
            }

            it("should update the target grid of the player") {
                verify(targetGrid).markWithStatus(attackedCell, cellStatus)
            }
        }
    }
})