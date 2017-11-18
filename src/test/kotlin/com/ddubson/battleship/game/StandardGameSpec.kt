package com.ddubson.battleship.game

import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

internal class StandardGameSpec: Spek ({
  given("a standard game") {
      val player1 = StandardPlayer("p1", mock {})
      val player2 = StandardPlayer("p2", mock {})


      on("choosing the next player and opponent") {
          val game = StandardGame(player1, player2)

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
          val game = StandardGame(player1, player2)

          it("should return player 2 when game is created") {
              assertEquals(player2, game.currentAttacker())
          }

          it("should return correct attacker after asking for next player") {
              game.nextPlayer()
              assertEquals(player1, game.currentAttacker())
          }
      }
  }
})
