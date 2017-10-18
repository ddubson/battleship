package com.ddubson.battleship.game.ship

interface ShipBuilder {
    fun newCarrier(): Carrier
    fun newCruiser(): Cruiser
    fun newDestroyer(): Destroyer
    fun newSubmarine(): Submarine
    fun newBattleship(): Battleship
}