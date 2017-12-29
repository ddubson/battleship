package com.ddubson.battleship.cli

import com.ddubson.battleship.game.core.BattleshipGameEngine
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class BattleshipGameCLIApplicationSpec : Spek({
    given("a battleship game application") {
        val gameEngine: BattleshipGameEngine = mock {}
        val application = BattleshipGameCLIApplication(gameEngine)

        application.run()

        on("run") {
            it("should call engage") {
                verify(gameEngine).engage()
            }
        }
    }
})