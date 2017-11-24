package com.ddubson.battleship.game.core.builders

import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.TargetGrid

interface PlayerBuilder {
    fun newPlayer(playerName: String,
                  oceanGrid: OceanGrid,
                  targetGrid: TargetGrid): Player
}