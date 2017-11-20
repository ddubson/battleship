package com.ddubson.battleship.game

class StandardPlayerBuilder : PlayerBuilder {
    override fun newPlayer(playerName: String,
                           oceanGrid: OceanGrid,
                           targetGrid: TargetGrid): Player =
            StandardPlayer(playerName, oceanGrid, targetGrid)
}