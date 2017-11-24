package com.ddubson.battleship.game.core.ship

import com.ddubson.battleship.game.core.ship.Ship

class Submarine : Ship {
    override fun type(): String = "Submarine"

    override fun length(): Int = 3
}