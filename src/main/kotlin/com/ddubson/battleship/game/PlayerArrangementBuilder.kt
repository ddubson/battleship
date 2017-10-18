package com.ddubson.battleship.game

interface PlayerArrangementBuilder {
    fun newPlayerArrangement(player: Player,
                             oceanGrid: OceanGrid,
                             targetGrid: TargetGrid): PlayerArrangement
}