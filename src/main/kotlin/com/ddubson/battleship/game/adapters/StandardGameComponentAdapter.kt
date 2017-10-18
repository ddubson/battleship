package com.ddubson.battleship.cli

import com.ddubson.battleship.game.*
import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.adapters.GameComponentAdapter

class StandardGameComponentAdapter(private val uiAdapter: BattleshipGameUiAdapter): GameComponentAdapter {
    override fun createPlayerOne(): Player = Player(uiAdapter.askForPlayerName())

    override fun createPlayerTwo(): Player = Player(uiAdapter.askForPlayerName())

    override fun createPlayerArrangement(player: Player, oceanGrid: OceanGrid, targetGrid: TargetGrid): PlayerArrangement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createTargetGrid(player: Player): TargetGrid {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createOceanGrid(player: Player): OceanGrid {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createGame(player1Arrangement: PlayerArrangement, player2Arrangement: PlayerArrangement): Game {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}