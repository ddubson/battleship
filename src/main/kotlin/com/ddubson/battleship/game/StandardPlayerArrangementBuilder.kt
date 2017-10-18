package com.ddubson.battleship.game

class StandardPlayerArrangementBuilder : PlayerArrangementBuilder {
    override fun newPlayerArrangement(player: Player, oceanGrid: OceanGrid, targetGrid: TargetGrid): PlayerArrangement {
        return StandardPlayerArrangement(player, oceanGrid, targetGrid)
    }
}