package com.ddubson.battleship.game.ship

class Battleship: Ship {
    override fun type(): String = "Battleship"

    override fun length(): Int = 4
}