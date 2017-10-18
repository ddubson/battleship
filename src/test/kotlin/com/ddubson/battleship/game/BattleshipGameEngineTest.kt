package com.ddubson.battleship.game

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class BattleshipGameEngineTest : Spek({
    val player1 = Player("player1")
    val player2 = Player("player2")

    given("battleship game engine") {
        on("engage") {
            it("should print Battleship game banner") {
                val uiAdapter: BattleshipGameUiAdapter = mock {
                    on { createPlayerOne() } doReturn player1
                    on { createPlayerTwo() } doReturn player2
                    on { askForCell() } doReturn Cell(0, 0)
                }
                val engine = BattleshipGameEngine(uiAdapter)
                engine.engage()
                verify(uiAdapter).printBanner()
            }

            it("should create and announce players") {
                val uiAdapter: BattleshipGameUiAdapter = mock {
                    on { createPlayerOne() } doReturn player1
                    on { createPlayerTwo() } doReturn player2
                    on { askForCell() } doReturn Cell(0, 0)
                }
                val engine = BattleshipGameEngine(uiAdapter)
                engine.engage()
                verify(uiAdapter).announcePlayer(player1)
                verify(uiAdapter).announcePlayer(player2)
            }

            it("should ask player 1 to place Carrier") {
                val uiAdapter: BattleshipGameUiAdapter = mock {
                    on { createPlayerOne() } doReturn player1
                    on { createPlayerTwo() } doReturn player2
                    on { askForCell() } doReturn Cell(0, 0)
                }
                val engine = BattleshipGameEngine(uiAdapter)
                engine.engage()
                verify(uiAdapter).placeShipBanner("Carrier")
            }
        }
    }
})