package com.ddubson.battleship.game.core.builders

import com.ddubson.battleship.game.core.ship.Battleship
import com.ddubson.battleship.game.core.ship.Carrier
import com.ddubson.battleship.game.core.ship.Cruiser
import com.ddubson.battleship.game.core.ship.Destroyer
import com.ddubson.battleship.game.core.ship.Submarine

interface ShipBuilder {
    fun newCarrier(): Carrier
    fun newCruiser(): Cruiser
    fun newDestroyer(): Destroyer
    fun newSubmarine(): Submarine
    fun newBattleship(): Battleship
}