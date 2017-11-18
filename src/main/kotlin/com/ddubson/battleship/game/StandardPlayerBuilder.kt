package com.ddubson.battleship.game

class StandardPlayerBuilder : PlayerBuilder {
    override fun newPlayer(playerName: String): Player = StandardPlayer(playerName)
}