package com.ddubson.battleship.game.core.ship

class Battleship: Ship {
    override fun type(): String = "Battleship"

    override fun length(): Int = 4
}