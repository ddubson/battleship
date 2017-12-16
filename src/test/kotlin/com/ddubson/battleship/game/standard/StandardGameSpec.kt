package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.InvalidInputException
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

internal class StandardGameSpec : Spek({
    given("a standard game") {
        val player1 = StandardPlayer("p1", mock {}, mock {})
        val player2 = StandardPlayer("p2", mock {}, mock {})

        on("choosing the next player and opponent") {
            val game = StandardGame(player1, player2, mock {})

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
            val game = StandardGame(player1, player2, mock {})

            it("should return player 2 when game is created") {
                assertEquals(player2, game.currentAttacker())
            }

            it("should return correct attacker after asking for next player") {
                game.nextPlayer()
                assertEquals(player1, game.currentAttacker())
            }
        }

        on("attack event") {
            val expectedCell = Cell(1, 1)
            val CLIAdapter: BattleshipGameCLIAdapter = mock {
                on { askForAttackCell() } doReturn expectedCell
            }
            val game = StandardGame(player1, player2, CLIAdapter)
            val actualCell = game.onAttackEvent()

            it("should prompt user to enter attack cell") {
                verify(CLIAdapter).askForAttackCell()
            }

            it("should return the created cell") {
                assertEquals(actualCell, expectedCell)
            }
        }

        on("attack event with bad input") {
            val cell = Cell(0, 0)
            val CLIAdapter: BattleshipGameCLIAdapter = mock {
                on { askForAttackCell() } doThrow InvalidInputException() doReturn cell
            }
            val game = StandardGame(mock {}, mock {}, CLIAdapter)
            game.onAttackEvent()

            it("should display a warning message that attack cell wasn't properly entered") {
                verify(CLIAdapter, times(2)).askForAttackCell()
                verify(CLIAdapter).displayWarning("Please enter attack cell in proper format.")
            }
        }

        on("after attack event") {
            val CLIAdapter: BattleshipGameCLIAdapter = mock {}
            val game = StandardGame(player1, player2, CLIAdapter)

            it("should display that the attacker missed if target cell is a miss") {
                game.afterAttackEvent(player1, player2, TargetCellStatus.MISS)
                verify(CLIAdapter).displayWarning("It's a miss!")
            }

            it("should display that the attacker hit if target cell is a hit") {
                game.afterAttackEvent(player1, player2, TargetCellStatus.HIT)
                verify(CLIAdapter).displayWarning("It's a hit!")
            }

            it("should display a warning if target cell is not a hit or miss") {
                game.afterAttackEvent(player1, player2, TargetCellStatus.OPEN)
                verify(CLIAdapter).displayWarning("Could not calculate attack result.")
            }
        }
    }
})
