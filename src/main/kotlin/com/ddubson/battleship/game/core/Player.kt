package com.ddubson.battleship.game.core

import com.ddubson.battleship.game.core.Attackable
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Subscribable
import com.ddubson.battleship.game.core.TargetGrid

interface Player: Attackable, Subscribable {
    fun playerName(): String
    fun oceanGrid(): OceanGrid
    fun targetGrid(): TargetGrid
    fun hasShipsLeft(): Boolean
}