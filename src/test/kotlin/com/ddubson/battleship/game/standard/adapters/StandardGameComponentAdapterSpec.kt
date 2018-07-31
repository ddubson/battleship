package com.ddubson.battleship.game.standard.adapters

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.standard.StandardPlayer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals

class StandardGameComponentAdapterSpec : Spek({
    given("standard game component adapter") {
        on("creating an ocean grid") {
            val oceanGrid: OceanGrid = mock {}

            val gridBuilder: GridBuilder = mock {
                on { newOceanGrid() } doReturn oceanGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, gridBuilder, mock {}, mock {})

            val actualOceanGrid = gameComponentAdapter.createOceanGrid()

            it("should create an empty Ocean Grid") {
                verify(gridBuilder).newOceanGrid()
            }

            it("should create an ocean grid") {
                assertEquals(oceanGrid, actualOceanGrid)
            }
        }

        on("creating a game") {
            val targetGrid: TargetGrid = mock {}
            val player1 = StandardPlayer("Player1", mock {}, targetGrid)
            val player2 = StandardPlayer("Player2", mock {}, targetGrid)
            val game: Game = mock {}
            val CLIAdapter: UserInterfaceAdapter = mock {}
            val gameBuilder: GameBuilder = mock {
                on { newGame(player1, player2, CLIAdapter) } doReturn game
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    CLIAdapter, gridBuilder, gameBuilder, mock {})

            val actualGame = gameComponentAdapter.createGame(player1, player2)

            it("should create a new game") {
                assertEquals(game, actualGame)
            }
        }

        on("creating player one") {
            val playerName = "Player 1"
            val player: Player = mock {}
            val targetGrid: TargetGrid = mock {}
            val oceanGrid: OceanGrid = mock {}
            val playerBuilder: PlayerBuilder = mock {
                on { newPlayer(playerName, oceanGrid, targetGrid) } doReturn player
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
                on { newOceanGrid() } doReturn oceanGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, gridBuilder, mock {}, playerBuilder)

            val actualPlayer = gameComponentAdapter.createPlayerOne(playerName, oceanGrid)

            it("should call player builder") {
                verify(playerBuilder).newPlayer(playerName, oceanGrid, targetGrid)
            }

            it("should return player") {
                assertEquals(player, actualPlayer)
            }
        }

        on("creating player two") {
            val playerName = "Player 2"
            val player: Player = mock {}
            val targetGrid: TargetGrid = mock {}
            val oceanGrid: OceanGrid = mock {}
            val playerBuilder: PlayerBuilder = mock {
                on { newPlayer(playerName, oceanGrid, targetGrid) } doReturn player
            }

            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
                on { newOceanGrid() } doReturn oceanGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, gridBuilder, mock {}, playerBuilder)

            val actualPlayer = gameComponentAdapter.createPlayerTwo(playerName, oceanGrid)

            it("should call player builder") {
                verify(playerBuilder).newPlayer(playerName, oceanGrid, targetGrid)
            }

            it("should return player") {
                assertEquals(player, actualPlayer)
            }
        }
    }
})