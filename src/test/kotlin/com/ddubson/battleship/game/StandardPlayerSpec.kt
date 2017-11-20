package com.ddubson.battleship.game

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals

internal class StandardPlayerSpec : Spek({
    given("a player") {
        on("returning player name") {
            val player = StandardPlayer("Player 1", mock{}, mock{})
            it("should return the player's name") {
                assertEquals("Player 1", player.playerName())
            }
        }

        on("attacking opponent") {
            val attackedCell = Cell(0, 1)
            val targetGrid: TargetGrid = mock {
                on { statusOf(attackedCell) } doReturn TargetCellStatus.OPEN
            }
            val subscribedGame: Game = mock {
                on { onAttackEvent() } doReturn attackedCell
            }

            val player = StandardPlayer("A Player", mock{}, targetGrid)
            val cellStatus = TargetCellStatus.HIT
            val opponent: Player = mock {
                on { receiveAttack(attackedCell) } doReturn cellStatus
            }

            player.subscribe(subscribedGame)
            player.attack(opponent)

            it("should ask the player to enter the cell to hit") {
                verify(subscribedGame).onAttackEvent()
            }

            it("should update the ocean grid of the opponent") {
                verify(opponent).receiveAttack(attackedCell)
            }

            it("should notify the game about the attack event") {
                verify(subscribedGame).afterAttackEvent(player, opponent, cellStatus)
            }

            it("should update the target grid of the player") {
                verify(targetGrid).markWithStatus(attackedCell, cellStatus)
            }
        }

        on("attacking the same cell twice") {
            it("should ask to enter another cell") {
                val attackedCell = Cell(0, 1)
                val subscribedGame: Game = mock {
                    on { onAttackEvent() } doReturn attackedCell
                }

                val opponent: Player = mock {
                    on { receiveAttack(attackedCell) } doReturn TargetCellStatus.MISS
                }
                val targetGrid: TargetGrid = mock {
                    on { statusOf(attackedCell) } doReturn TargetCellStatus.MISS doReturn TargetCellStatus.OPEN
                }
                val player = StandardPlayer("A Player", mock{}, targetGrid)
                player.subscribe(subscribedGame)

                player.attack(opponent)

                verify(subscribedGame, times(2)).onAttackEvent()
            }
        }

        on("receiving attack") {
            val attackedCell = Cell(0, 1)

            it("should report a 'hit' if ocean grid reports a hit") {
                val oceanGrid: OceanGrid = mock {
                    on { bombard(attackedCell) } doReturn AttackStatus.HIT
                }
                val player = StandardPlayer("a player", oceanGrid, mock {})

                assertEquals(TargetCellStatus.HIT, player.receiveAttack(attackedCell))
            }

            it("should report a 'miss' if ocean grid reports a miss") {
                val oceanGrid: OceanGrid = mock {
                    on { bombard(attackedCell) } doReturn AttackStatus.MISS
                }
                val player = StandardPlayer("a player", oceanGrid, mock {})

                assertEquals(TargetCellStatus.MISS, player.receiveAttack(attackedCell))
            }
        }

        on("evaluating if any ships are left in battlespace") {

            it("should evaluate to true if there are ships left") {
                val oceanGrid: OceanGrid = mock {
                    on { hasEngagedCells() } doReturn true
                }
                val player = StandardPlayer("p1", oceanGrid, mock {})

                assertTrue(player.hasShipsLeft())
            }

            it("should evaluate to false if there are no ships left") {
                val oceanGrid: OceanGrid = mock {
                    on { hasEngagedCells() } doReturn false
                }
                val player = StandardPlayer("p1", oceanGrid, mock {})

                assertFalse(player.hasShipsLeft())
            }
        }
    }
})