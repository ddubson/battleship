package com.ddubson.battleship.cli

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal class UnixClearScreenSpec: Spek({
    given("Unix Clear Screen") {
        on("clear") {
            val cliAdapter: SystemCLIAdapter = mock {}
            val clearScreen = UnixClearScreen(cliAdapter)

            clearScreen.clear()
            it("should print clear screen code reserved for Unix") {
                verify(cliAdapter).print(UnixClearScreen.ANSI_CLS + UnixClearScreen.ANSI_HOME)
                verify(cliAdapter).flush()
            }
        }
    }
})