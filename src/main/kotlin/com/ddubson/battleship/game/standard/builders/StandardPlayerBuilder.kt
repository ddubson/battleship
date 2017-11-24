package com.ddubson.battleship.game.standard.builders

import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.standard.StandardPlayer

class StandardPlayerBuilder : PlayerBuilder {
    override fun newPlayer(playerName: String,
                           oceanGrid: OceanGrid,
                           targetGrid: TargetGrid): Player =
            StandardPlayer(playerName, oceanGrid, targetGrid)
}