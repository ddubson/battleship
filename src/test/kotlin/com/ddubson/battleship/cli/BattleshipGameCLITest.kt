package com.ddubson.battleship.cli

import com.ddubson.battleship.game.Cell
import com.ddubson.battleship.game.Direction
import com.ddubson.battleship.game.OceanGrid
import com.ddubson.battleship.game.Player
import com.ddubson.battleship.game.ship.Carrier
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals

class BattleshipGameCLITest : Spek({
    given("a battleship game cli") {
        on("ask for player name") {
            val cliAdapter: CLIAdapter = mock { on { readLine() } doReturn "playerName" }
            val battleshipGameCli = BattleshipGameCLI(cliAdapter)
            val actualPlayerName = battleshipGameCli.askForPlayerName()

            it("should prompt for player to enter name") {
                verify(cliAdapter).print("Enter player name: ")
            }

            it("should return proper player input") {
                assertEquals("playerName", actualPlayerName)
            }
        }

        on("ask for direction") {
            val cliAdapter: CLIAdapter = mock { on { readLine() } doReturn "h" }
            val battleshipGameCli = BattleshipGameCLI(cliAdapter)
            val ship = Carrier()
            val actualDirection = battleshipGameCli.askForDirection(ship)

            it("should prompt for direction") {
                verify(cliAdapter).print("Enter direction for ${ship.type()} [h|v]: ")
            }

            it("should return a horizontal direction based on input of 'h'") {
                assertEquals(Direction.HORIZONTAL, actualDirection)
            }
        }

        on("ask for cell") {
            val cliAdapter: CLIAdapter = mock { on { readLine() } doReturn "0 1" }
            val battleshipGameCli = BattleshipGameCLI(cliAdapter)
            val ship = Carrier()
            val expectedCell = Cell(0, 1)
            val actualCell = battleshipGameCli.askForCell(ship)

            it("should prompt to enter the cell coordinates") {
                verify(cliAdapter).print("Enter initial coordinates for ${ship.type()} (e.g. '1 2' for [1,2]): ")
            }

            it("should return a cell given proper input") {
                assertEquals(actualCell.x, expectedCell.x)
                assertEquals(actualCell.y, expectedCell.y)
            }
        }

        on("output only") {
            val cliAdapter: CLIAdapter = mock {}
            val battleshipGameCli = BattleshipGameCLI(cliAdapter)

            it("should display place ship banner notice") {
                battleshipGameCli.placeShipBanner("Carrier")
                verify(cliAdapter).println("Enter Carrier coordinates...")
            }

            it("should announce player") {
                val player: Player = mock {
                    on { playerName() } doReturn "Player 1"
                }
                battleshipGameCli.announcePlayer(player)

                verify(cliAdapter).println("Player Player 1 has entered the battlespace!")
            }

            it("should print the game banner") {
                battleshipGameCli.printBanner()
                verify(cliAdapter).println("--- Welcome to Battleship! ---")
            }

            it("should display warnings") {
                val message = "Some message"
                battleshipGameCli.displayWarning(message)
                verify(cliAdapter).println(message)
            }

            it("should display an ocean grid") {
                val printedOceanGrid = "grid..."
                val oceanGrid: OceanGrid = mock {
                    on { as2DString() } doReturn printedOceanGrid
                }

                battleshipGameCli.displayOceanGrid(oceanGrid)

                verify(cliAdapter).println(printedOceanGrid)
            }
        }

    }
})