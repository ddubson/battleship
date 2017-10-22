package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter

class StandardPlayer(private val playerName: String,
                     private val uiAdapter: BattleshipGameUiAdapter) : Player {

    private var oceanGrid: OceanGrid? = null
    private var targetGrid: TargetGrid? = null

    override fun targetGrid(): TargetGrid? = targetGrid

    override fun setOceanGrid(oceanGrid: OceanGrid) {
        this.oceanGrid = oceanGrid
    }

    override fun setTargetGrid(targetGrid: TargetGrid) {
        this.targetGrid = targetGrid
    }

    override fun oceanGrid(): OceanGrid? = oceanGrid

    override fun playerName(): String = playerName

    override fun takeTurn(opponent: Player) {
        val cell = uiAdapter.askForAttackCell()
        //val cellStatus = opponent.receiveAttack(cell)
        //player.updateTargetGrid(cell)
    }
}