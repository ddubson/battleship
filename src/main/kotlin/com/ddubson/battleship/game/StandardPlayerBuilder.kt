package com.ddubson.battleship.game

class StandardPlayerBuilder : PlayerBuilder {
    override fun newPlayer(playerName: String, targetGrid: TargetGrid): Player =
            StandardPlayer(playerName, targetGrid)
}