package com.ddubson.battleship.game

interface BattleshipGameUiAdapter {
    fun printBanner()

    fun createPlayerOne(): Player

    fun createPlayerTwo(): Player

    fun announcePlayer(player: Player)
}