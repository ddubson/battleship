package com.ddubson.battleship.cli

interface CLIAdapter {
    fun print(msg: String)
    fun println(msg: String)
    fun readLine(): String
}

open class StandardCLIAdapter : CLIAdapter {
    override fun print(msg: String) {
        kotlin.io.print(msg)
    }

    override fun println(msg: String) {
        kotlin.io.println(msg)
    }

    override fun readLine(): String {
        return kotlin.io.readLine()!!
    }
}