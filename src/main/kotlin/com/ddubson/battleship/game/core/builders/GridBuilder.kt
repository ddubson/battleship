package com.ddubson.battleship.game.core.builders

import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.TargetGrid

interface GridBuilder {
    fun newOceanGrid(): OceanGrid
    fun newTargetGrid(): TargetGrid
}