package com.ddubson.battleship.game.standard.builders

import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.core.ship.Battleship
import com.ddubson.battleship.game.core.ship.Carrier
import com.ddubson.battleship.game.core.ship.Cruiser
import com.ddubson.battleship.game.core.ship.Destroyer
import com.ddubson.battleship.game.core.ship.Submarine

class StandardShipBuilder : ShipBuilder {
    override fun newCarrier(): Carrier = Carrier()
    override fun newCruiser(): Cruiser = Cruiser()
    override fun newDestroyer(): Destroyer = Destroyer()
    override fun newSubmarine(): Submarine = Submarine()
    override fun newBattleship(): Battleship = Battleship()
}