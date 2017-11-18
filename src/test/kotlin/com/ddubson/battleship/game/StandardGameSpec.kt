package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

internal class StandardGameSpec : Spek({
    given("a standard game") {
        val player1 = StandardPlayer("p1")
        val player2 = StandardPlayer("p2")


        on("choosing the next player and opponent") {
            val game = StandardGame(player1, player2, mock{})

            it("should rotate players") {
                assertEquals(player1, game.nextPlayer())
                assertEquals(player2, game.currentOpponent())
                assertEquals(player2, game.nextPlayer())
                assertEquals(player1, game.currentOpponent())
                assertEquals(player1, game.nextPlayer())
                assertEquals(player2, game.currentOpponent())
            }
        }

        on("calling current attacker") {
            val game = StandardGame(player1, player2, mock{})

            it("should return player 2 when game is created") {
                assertEquals(player2, game.currentAttacker())
            }

            it("should return correct attacker after asking for next player") {
                game.nextPlayer()
                assertEquals(player1, game.currentAttacker())
            }
        }

        on("attack event") {
            val expectedCell = Cell(1,1)
            val uiAdapter: BattleshipGameUiAdapter = mock {
                on { askForAttackCell() } doReturn expectedCell
            }
            val game = StandardGame(player1, player2, uiAdapter)
            val actualCell = game.onAttackEvent()

            it("should prompt user to enter attack cell") {
                verify(uiAdapter).askForAttackCell()
            }

            it("should return the created cell") {
                assertEquals(actualCell, expectedCell)
            }
        }

        on("after attack event") {
            val uiAdapter: BattleshipGameUiAdapter = mock {}
            val game = StandardGame(player1, player2, uiAdapter)

            it("should display that the attacker missed if target cell is a miss") {
                game.afterAttackEvent(player1, player2, TargetCellStatus.MISS)
                verify(uiAdapter).displayWarning("It's a miss!")
            }

            it("should display that the attacker hit if target cell is a hit") {
                game.afterAttackEvent(player1, player2, TargetCellStatus.HIT)
                verify(uiAdapter).displayWarning("It's a hit!")
            }

            it("should display a warning if target cell is not a hit or miss") {
                game.afterAttackEvent(player1, player2, TargetCellStatus.OPEN)
                verify(uiAdapter).displayWarning("Could not calculate attack result.")
            }
        }
    }
})
