package com.ddubson.battleship.cli

interface CLIAdapter {
    fun print(msg: String)
    fun println(msg: String)
    fun readLine(): String
    fun flush()
}

open class StandardCLIAdapter : CLIAdapter {
    override fun flush() {
        System.out.flush()
    }

    override fun print(msg: String) {
        kotlin.io.print(msg)
    }

    override fun println(msg: String) {
        kotlin.io.println(msg)
    }

    override fun readLine(): String = kotlin.io.readLine()!!
}