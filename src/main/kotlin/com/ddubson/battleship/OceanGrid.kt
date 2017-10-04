package com.ddubson.battleship

class OceanGrid {
    val size: Int = 8
    lateinit var carrierPosition: List<Cell>

    fun placeCarrier(cells: List<Cell>) {
        this.carrierPosition = cells
    }
}