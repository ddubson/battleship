package com.ddubson.battleship.game.ship

class Submarine : Ship {
    override fun type(): String = "Submarine"

    override fun length(): Int = 3
}