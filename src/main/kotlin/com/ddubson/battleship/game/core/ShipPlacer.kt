package com.ddubson.battleship.game.core

import com.ddubson.battleship.game.core.ship.Ship

interface ShipPlacer {
    fun place(oceanGrid: OceanGrid, ship: Ship)
}