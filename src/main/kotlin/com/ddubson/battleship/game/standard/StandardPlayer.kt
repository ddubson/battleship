package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.cell.TargetCellStatus.HIT
import com.ddubson.battleship.game.core.cell.TargetCellStatus.MISS
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.Subscriber
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.cell.AttackStatus
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus

class StandardPlayer(private val playerName: String,
                     private val oceanGrid: OceanGrid,
                     private val targetGrid: TargetGrid) : Player {
    private lateinit var subscribedGame: Subscriber

    override fun subscribe(subscriber: Subscriber) {
        this.subscribedGame = subscriber
    }

    override fun hasShipsLeft(): Boolean = this.oceanGrid.hasEngagedCells()

    override fun targetGrid(): TargetGrid = targetGrid

    override fun oceanGrid(): OceanGrid = oceanGrid

    override fun playerName(): String = playerName

    private fun updateTargetGrid(cell: Cell, targetCellStatus: TargetCellStatus) {
        this.targetGrid.markWithStatus(cell, targetCellStatus)
    }

    override fun attack(opponent: Player) {
        var cell: Cell

        do {
            cell = subscribedGame.onAttackEvent()
        } while (cellHasAlreadyBeenFiredAt(cell))

        val cellStatus = opponent.receiveAttack(cell)
        subscribedGame.afterAttackEvent(this, opponent, cellStatus)

        this.updateTargetGrid(cell, cellStatus)
    }

    override fun receiveAttack(cell: Cell): TargetCellStatus {
        val status = this.oceanGrid.bombard(cell)
        return when(status) {
            AttackStatus.HIT -> HIT
            else -> MISS
        }
    }

    private fun cellHasAlreadyBeenFiredAt(cell: Cell): Boolean {
        val status =  this.targetGrid.statusOf(cell)
        return status == HIT || status == MISS
    }
}