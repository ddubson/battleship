package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
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

        on("attacking opponent") {
            val attackedCell = Cell(0, 1)
            val uiAdapter: BattleshipGameUiAdapter = mock {
                on { askForAttackCell() } doReturn attackedCell
            }
            val player = StandardPlayer("A Player", uiAdapter)
            val cellStatus = TargetCellStatus.HIT
            val targetGrid: TargetGrid = mock {
                on { statusOf(attackedCell) } doReturn TargetCellStatus.OPEN
            }
            val opponent: Player = mock {
                on { receiveAttack(attackedCell) } doReturn cellStatus
            }

            player.setOceanGrid(StandardOceanGrid())
            player.setTargetGrid(targetGrid)
            player.attack(opponent)

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

        on("attacking the same cell twice") {
            it("should ask to enter another cell") {
                val attackedCell = Cell(0, 1)
                val uiAdapter: BattleshipGameUiAdapter = mock {
                    on { askForAttackCell() } doReturn attackedCell
                }
                val opponent: Player = mock {
                    on { receiveAttack(attackedCell) } doReturn TargetCellStatus.MISS
                }
                val player = StandardPlayer("A Player", uiAdapter)
                val targetGrid: TargetGrid = mock {
                    on { statusOf(attackedCell) } doReturn TargetCellStatus.MISS doReturn TargetCellStatus.OPEN
                }
                player.setTargetGrid(targetGrid)

                player.attack(opponent)

                verify(uiAdapter, times(2)).askForAttackCell()
            }
        }

        on("receiving attack") {
            val attackedCell = Cell(0, 1)
            val uiAdapter: BattleshipGameUiAdapter = mock {}
            val player = StandardPlayer("a player", uiAdapter)

            it("should report a 'hit' if ocean grid reports a hit") {
                val oceanGrid: OceanGrid = mock {
                    on { bombard(attackedCell) } doReturn AttackStatus.HIT
                }
                player.setOceanGrid(oceanGrid)

                assertEquals(TargetCellStatus.HIT, player.receiveAttack(attackedCell))
            }

            it("should report a 'miss' if ocean grid reports a miss") {
                val oceanGrid: OceanGrid = mock {
                    on { bombard(attackedCell) } doReturn AttackStatus.MISS
                }
                player.setOceanGrid(oceanGrid)

                assertEquals(TargetCellStatus.MISS, player.receiveAttack(attackedCell))
            }
        }

    }
})