package com.ddubson.battleship.game.ship

class Carrier: Ship {
    override fun type(): String = "Carrier"

    override fun length(): Int = 5
}