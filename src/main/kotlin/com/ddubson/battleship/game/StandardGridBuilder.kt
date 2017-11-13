package com.ddubson.battleship.game

class StandardGridBuilder: GridBuilder {
    override fun newOceanGrid(): OceanGrid = StandardOceanGrid()
    override fun newTargetGrid(): TargetGrid = StandardTargetGrid()
}