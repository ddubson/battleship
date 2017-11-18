package com.ddubson.battleship.game

import java.util.*
import java.util.concurrent.ArrayBlockingQueue

class StandardGame(player1: Player,
                   player2: Player) : Game {
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
}