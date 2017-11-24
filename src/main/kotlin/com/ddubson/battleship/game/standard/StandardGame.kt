package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus
import java.util.*
import java.util.concurrent.ArrayBlockingQueue

class StandardGame(player1: Player,
                   player2: Player,
                   private val uiAdapter: BattleshipGameUiAdapter) : Game {
    private val turnQueue: Queue<Player>

    init {
        turnQueue = ArrayBlockingQueue<Player>(2)
        turnQueue.add(player2)
        turnQueue.add(player1)
    }

    override fun currentOpponent(): Player = turnQueue.elementAt(1)

    override fun currentAttacker(): Player = turnQueue.elementAt(0)

    override fun nextPlayer(): Player {
        turnQueue.add(turnQueue.remove())
        return turnQueue.element()
    }

    override fun onAttackEvent(): Cell = uiAdapter.askForAttackCell()

    override fun afterAttackEvent(attacker: Player, opponent: Player, cellStatus: TargetCellStatus) {
        when(cellStatus) {
            TargetCellStatus.MISS -> uiAdapter.displayWarning("It's a miss!")
            TargetCellStatus.HIT -> uiAdapter.displayWarning("It's a hit!")
            else -> uiAdapter.displayWarning("Could not calculate attack result.")
        }
    }
}