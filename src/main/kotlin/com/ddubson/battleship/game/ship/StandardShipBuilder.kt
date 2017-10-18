package com.ddubson.battleship.game.ship

class StandardShipBuilder : ShipBuilder{
    override fun newCarrier(): Carrier = Carrier()
    override fun newCruiser(): Cruiser = Cruiser()
    override fun newDestroyer(): Destroyer = Destroyer()
    override fun newSubmarine(): Submarine = Submarine()
    override fun newBattleship(): Battleship = Battleship()
}