package com.ddubson.battleship.cli

import com.ddubson.battleship.game.core.Direction
import com.ddubson.battleship.game.core.InvalidInputException
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.Carrier
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows

class CLIUserInterfaceAdapterTest : Spek({
    given("a battleship game cli") {
        on("ask for player name") {
            val cliAdapter: SystemCLIAdapter = mock { on { readLine() } doReturn "playerName" }
            val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})
            val actualPlayerName = cliUserInterfaceAdapter.askForPlayerName()

            it("should prompt for player to enter name") {
                verify(cliAdapter).print("Enter player name: ")
            }

            it("should return proper player input") {
                assertEquals("playerName", actualPlayerName)
            }
        }

        on("ask for direction") {
            val cliAdapter: SystemCLIAdapter = mock { on { readLine() } doReturn "h" }
            val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})
            val ship = Carrier()
            val actualDirection = cliUserInterfaceAdapter.askForDirection(ship)

            it("should prompt for direction") {
                verify(cliAdapter).print("Enter direction for ${ship.type()} [h|v]: ")
            }

            it("should return a horizontal direction based on input of 'h'") {
                assertEquals(Direction.HORIZONTAL, actualDirection)
            }
        }

        on("ask for cell") {
            val cliAdapter: SystemCLIAdapter = mock { on { readLine() } doReturn "0 1" }
            val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})
            val ship = Carrier()
            val expectedCell = Cell(0, 1)
            val actualCell = cliUserInterfaceAdapter.askForCell(ship)

            it("should prompt to enter the cell coordinates") {
                verify(cliAdapter).print("Enter initial coordinates for ${ship.type()} (e.g. '1 2' for [1,2]): ")
            }

            it("should return a cell given proper input") {
                assertEquals(actualCell.x, expectedCell.x)
                assertEquals(actualCell.y, expectedCell.y)
            }
        }

        on("user entering wrongly formatted cell") {
            it("should throw an error for an empty string") {
                val cliAdapter: SystemCLIAdapter = mock { on { readLine() } doReturn "" }
                assertInvalidInput(cliAdapter)
            }

            it("should throw an error for a non-number") {
                val cliAdapter: SystemCLIAdapter = mock { on { readLine() } doReturn "A" }
                assertInvalidInput(cliAdapter)
            }

            it("should throw an error for a mix of alphas") {
                val cliAdapter: SystemCLIAdapter = mock { on { readLine() } doReturn "A 1" }
                assertInvalidInput(cliAdapter)
            }
        }

        on("ask for attack cell") {
            val cliAdapter: SystemCLIAdapter = mock { on { readLine() } doReturn "0 1" }
            val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})
            val expectedCell = Cell(0, 1)
            val actualCell = cliUserInterfaceAdapter.askForAttackCell()

            it("should prompt the user to enter an attack cell") {
                verify(cliAdapter).print("Enter attack cell coordinates: ")
            }

            it("should return the expected cell") {
                assertEquals(actualCell.x, expectedCell.x)
                assertEquals(actualCell.y, expectedCell.y)
            }
        }

        on("output only") {

            it("should display place ship banner notice") {
                val cliAdapter: SystemCLIAdapter = mock {}
                val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})

                cliUserInterfaceAdapter.placeShipBanner("Carrier")
                verify(cliAdapter).println("Enter Carrier coordinates...")
            }

            it("should announce player") {
                val cliAdapter: SystemCLIAdapter = mock {}
                val clearScreen: ClearScreen = mock {}
                val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, clearScreen)
                val player: Player = mock {
                    on { playerName() } doReturn "Player 1"
                }
                cliUserInterfaceAdapter.announcePlayer(player)

                verify(clearScreen).clear()
                verify(cliAdapter).println("Player Player 1 has entered the battlespace!")
            }

            it("should print the game banner") {
                val cliAdapter: SystemCLIAdapter = mock {}
                val clearScreen: ClearScreen = mock {}
                val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, clearScreen)
                cliUserInterfaceAdapter.printBanner()

                verify(clearScreen).clear()
                verify(cliAdapter).println("--- Welcome to Battleship! ---")
            }

            it("should display warnings") {
                val cliAdapter: SystemCLIAdapter = mock {}
                val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})
                val message = "Some message"
                cliUserInterfaceAdapter.displayWarning(message)
                verify(cliAdapter).println(message)
            }

            it("should display an ocean grid") {
                val cliAdapter: SystemCLIAdapter = mock {}
                val clearScreen: ClearScreen = mock {}
                val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, clearScreen)

                val printedOceanGrid = "grid..."
                val oceanGrid: OceanGrid = mock {
                    on { as2DString() } doReturn printedOceanGrid
                }

                cliUserInterfaceAdapter.displayOceanGrid(oceanGrid)

                verify(clearScreen).clear()
                verify(cliAdapter).println(printedOceanGrid)
            }

            it("should display a target grid") {
                val cliAdapter: SystemCLIAdapter = mock {}
                val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})

                val printedTargetGrid = "grid..."
                val targetGrid: TargetGrid = mock {
                    on { as2DString() } doReturn printedTargetGrid
                }

                cliUserInterfaceAdapter.displayTargetGrid(targetGrid)

                verify(cliAdapter).println(printedTargetGrid)
            }

            it("should announce the winner of a game") {
                val cliAdapter: SystemCLIAdapter = mock {}
                val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})
                val player: Player = mock {
                    on { playerName() } doReturn "Player 1"
                }

                cliUserInterfaceAdapter.announceWinner(player)

                verify(cliAdapter).println("#### Player ${player.playerName()} wins! ####")
            }
        }
    }
})

fun assertInvalidInput(cliAdapter: SystemCLIAdapter) {
    val cliUserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter, mock {})
    assertThrows(InvalidInputException::class.java) {
        cliUserInterfaceAdapter.askForCell(Carrier())
    }
}