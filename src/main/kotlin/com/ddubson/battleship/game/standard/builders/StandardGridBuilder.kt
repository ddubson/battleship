package com.ddubson.battleship.game.standard.builders

import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.standard.StandardOceanGrid
import com.ddubson.battleship.game.standard.StandardTargetGrid

class StandardGridBuilder: GridBuilder {
    override fun newOceanGrid(): OceanGrid = StandardOceanGrid()
    override fun newTargetGrid(): TargetGrid = StandardTargetGrid()
}