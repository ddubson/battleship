package com.ddubson.battleship.game

class StandardGridBuilder: GridBuilder {
    override fun newOceanGrid(): OceanGrid {
        return StandardOceanGrid()
    }

    override fun newTargetGrid(): TargetGrid {
        return StandardTargetGrid()
    }
}