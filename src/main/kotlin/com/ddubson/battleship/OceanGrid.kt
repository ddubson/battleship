package com.ddubson.battleship

class OceanGrid {
    val size: Int = 8
    lateinit var carrierPosition: List<Cell>
    lateinit var battleshipPosition: List<Cell>
    lateinit var cruiserPosition: List<Cell>
    lateinit var submarinePosition: List<Cell>
    lateinit var destroyerPosition: List<Cell>

    fun placeCarrier(cells: List<Cell>) {
        this.carrierPosition = cells
    }

    fun placeBattleship(cells: List<Cell>) {
        this.battleshipPosition = cells
    }

    fun placeCruiser(cells: List<Cell>) {
        this.cruiserPosition = cells
    }

    fun placeSubmarine(cells: List<Cell>) {
        this.submarinePosition = cells
    }

    fun placeDestroyer(cells: List<Cell>) {
        this.destroyerPosition = cells
    }
}