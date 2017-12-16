package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.InvalidInputException
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.cell.TargetCellStatus
import java.util.Queue
import java.util.concurrent.ArrayBlockingQueue

class StandardGame(player1: Player,
                   player2: Player,
                   private val CLIAdapter: BattleshipGameCLIAdapter) : Game {
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

    override fun onAttackEvent(): Cell {
        while (true) {
            try {
                return CLIAdapter.askForAttackCell()
            } catch (e: InvalidInputException) {
                CLIAdapter.displayWarning("Please enter attack cell in proper format.")
            }
        }
    }

    override fun afterAttackEvent(attacker: Player, opponent: Player, cellStatus: TargetCellStatus) {
        when (cellStatus) {
            TargetCellStatus.MISS -> CLIAdapter.displayWarning("It's a miss!")
            TargetCellStatus.HIT -> CLIAdapter.displayWarning("It's a hit!")
            else -> CLIAdapter.displayWarning("Could not calculate attack result.")
        }
    }
}
