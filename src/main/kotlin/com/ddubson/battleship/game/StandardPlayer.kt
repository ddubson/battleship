package com.ddubson.battleship.game

import com.ddubson.battleship.game.TargetCellStatus.HIT
import com.ddubson.battleship.game.TargetCellStatus.MISS
import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter

class StandardPlayer(private val playerName: String,
                     private val uiAdapter: BattleshipGameUiAdapter) : Player {
    private var oceanGrid: OceanGrid? = null
    private var targetGrid: TargetGrid? = null

    override fun hasShipsLeft(): Boolean = this.oceanGrid!!.hasEngagedCells()

    override fun targetGrid(): TargetGrid? = targetGrid

    override fun setOceanGrid(oceanGrid: OceanGrid) {
        this.oceanGrid = oceanGrid
    }

    override fun setTargetGrid(targetGrid: TargetGrid) {
        this.targetGrid = targetGrid
    }

    override fun oceanGrid(): OceanGrid? = oceanGrid

    override fun playerName(): String = playerName

    private fun updateTargetGrid(cell: Cell, targetCellStatus: TargetCellStatus) {
        this.targetGrid!!.markWithStatus(cell, targetCellStatus)
    }

    override fun attack(opponent: Player) {
        var cell: Cell

        do {
            cell = uiAdapter.askForAttackCell()
        } while (cellHasAlreadyBeenFiredAt(cell))

        val cellStatus = opponent.receiveAttack(cell)

        this.updateTargetGrid(cell, cellStatus)

    }

    override fun receiveAttack(cell: Cell): TargetCellStatus {
        val status = this.oceanGrid!!.bombard(cell)
        return when(status) {
            AttackStatus.HIT -> HIT
            else -> MISS
        }
    }

    private fun cellHasAlreadyBeenFiredAt(cell: Cell): Boolean {
        val status =  this.targetGrid!!.statusOf(cell)
        return status == HIT || status == MISS
    }
}