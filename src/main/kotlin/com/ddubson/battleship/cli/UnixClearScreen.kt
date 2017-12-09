package com.ddubson.battleship.cli

class UnixClearScreen(private val cliAdapter: SystemCLIAdapter) : ClearScreen {

    override fun clear() {
        cliAdapter.print(Companion.ANSI_CLS + Companion.ANSI_HOME)
        cliAdapter.flush()
    }

    companion object {
        val ANSI_CLS = "\u001b[2J"
        val ANSI_HOME = "\u001b[H"
    }
}