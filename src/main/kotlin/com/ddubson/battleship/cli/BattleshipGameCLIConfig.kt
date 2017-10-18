package com.ddubson.battleship.cli

import com.ddubson.battleship.game.*
import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.adapters.GameComponentAdapter
import com.ddubson.battleship.game.adapters.StandardGameComponentAdapter
import com.ddubson.battleship.game.ship.ShipBuilder
import com.ddubson.battleship.game.ship.StandardShipBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BattleshipGameCLIConfig {
    @Bean
    fun gameEngine(): BattleshipGameEngine = BattleshipGameEngine(
            battleshipGameUiAdapter(), gameComponentAdapter())

    @Bean
    fun battleshipGameUiAdapter(): BattleshipGameUiAdapter = BattleshipGameCLI()

    @Bean
    fun gameComponentAdapter(): GameComponentAdapter = StandardGameComponentAdapter(
            battleshipGameUiAdapter(), shipBuilder(), gridBuilder(), playerArrangementBuilder(), gameBuilder())

    @Bean
    fun gridBuilder(): GridBuilder = StandardGridBuilder()

    @Bean
    fun shipBuilder(): ShipBuilder = StandardShipBuilder()

    @Bean
    fun playerArrangementBuilder(): PlayerArrangementBuilder = StandardPlayerArrangementBuilder()

    @Bean
    fun gameBuilder(): GameBuilder = StandardGameBuilder()
}