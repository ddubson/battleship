package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.Ship

class OceanGrid {
    val size: Int = 8
    private val ships = mutableMapOf<String, List<Cell>>()

    fun carrierPosition(): List<Cell> = ships["Carrier"].orEmpty()

    fun battleshipPosition(): List<Cell> = ships["Battleship"].orEmpty()

    fun cruiserPosition(): List<Cell> = ships["Cruiser"].orEmpty()

    fun submarinePosition(): List<Cell> = ships["Submarine"].orEmpty()

    fun destroyerPosition(): List<Cell> = ships["Destroyer"].orEmpty()

    fun place(ship: Ship, initialCell: Cell, direction: Direction) {
        if(ships.containsKey(ship.type())) {
            throw ShipAlreadyPlacedException()
        }

        ships.put(ship.type(), (0 until ship.length()).map {
            if (Direction.VERTICAL == direction) {
                Cell(initialCell.x, initialCell.y + it)
            } else {
                Cell(initialCell.x + it, initialCell.y)
            }
        })
    }
}