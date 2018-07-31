package com.ddubson.battleship.cli.unix

import com.ddubson.battleship.cli.ClearScreen
import com.ddubson.battleship.cli.SystemCLIAdapter

class UnixClearScreen(private val cliAdapter: SystemCLIAdapter) : ClearScreen {

    override fun clear() {
        cliAdapter.print(ANSI_CLS + ANSI_HOME)
        cliAdapter.flush()
    }

    companion object {
        val ANSI_CLS = "\u001b[2J"
        val ANSI_HOME = "\u001b[H"
    }
}