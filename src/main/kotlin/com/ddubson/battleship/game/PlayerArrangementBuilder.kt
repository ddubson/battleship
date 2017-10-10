package com.ddubson.battleship.game

class PlayerArrangementBuilder {
    private var playerArrangement: PlayerArrangement = PlayerArrangement()

    fun player(player: Player): PlayerArrangementBuilder {
        playerArrangement.player = player
        return this
    }

    fun oceanGrid(oceanGrid: OceanGrid): PlayerArrangementBuilder {
        playerArrangement.oceanGrid = oceanGrid
        return this
    }

    fun targetGrid(targetGrid: TargetGrid): PlayerArrangementBuilder {
        playerArrangement.targetGrid = targetGrid
        return this
    }

    fun build(): PlayerArrangement {
        return playerArrangement
    }
}