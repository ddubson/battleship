package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter

class StandardPlayer(private val playerName: String,
                     private val uiAdapter: BattleshipGameUiAdapter) : Player, TakeTurnable {
    private  var oceanGrid: OceanGrid? = null
    private  var targetGrid: TargetGrid? = null

    override fun targetGrid(): TargetGrid? = targetGrid

    override fun setOceanGrid(oceanGrid: OceanGrid) {
        this.oceanGrid = oceanGrid
    }

    override fun setTargetGrid(targetGrid: TargetGrid) {
        this.targetGrid = targetGrid
    }

    override fun oceanGrid(): OceanGrid? = oceanGrid

    override fun playerName(): String = playerName

    private fun updateTargetGrid(cell: Cell, cellStatus: CellStatus) {
        this.targetGrid!!.markWithStatus(cell, cellStatus)
    }

    override fun takeTurn(opponent: Player) {
        var cell: Cell

        do {
            cell = uiAdapter.askForAttackCell()
        } while (cellHasAlreadyBeenFiredAt(cell))

        // update opponent ocean grid
        val cellStatus = opponent.receiveAttack(cell)

        // update target grid
        this.updateTargetGrid(cell, cellStatus)

    }

    override fun receiveAttack(cell: Cell): CellStatus {
        // Check ocean grid if cell hits upon a ship
        return StandardCellStatus()
    }

    private fun cellHasAlreadyBeenFiredAt(cell: Cell): Boolean {
        return false
    }
}