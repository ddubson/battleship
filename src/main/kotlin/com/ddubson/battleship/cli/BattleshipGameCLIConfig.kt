package com.ddubson.battleship.cli

import com.ddubson.battleship.game.core.ShipPlacer
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.standard.BattleshipGameEngine
import com.ddubson.battleship.game.standard.StandardShipPlacer
import com.ddubson.battleship.game.standard.adapters.StandardGameComponentAdapter
import com.ddubson.battleship.game.standard.builders.StandardGameBuilder
import com.ddubson.battleship.game.standard.builders.StandardGridBuilder
import com.ddubson.battleship.game.standard.builders.StandardPlayerBuilder
import com.ddubson.battleship.game.standard.builders.StandardShipBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BattleshipGameCLIConfig {
    @Bean
    fun gameEngine(): BattleshipGameEngine = BattleshipGameEngine(
            battleshipGameCliAdapter(), gameComponentAdapter(), shipBuilder(), shipPlacer())

    @Bean
    fun battleshipGameCliAdapter(): BattleshipGameCLIAdapter = BattleshipGameCLI(cliAdapter(), clearScreen())

    @Bean
    fun gameComponentAdapter(): GameComponentAdapter = StandardGameComponentAdapter(
            battleshipGameCliAdapter(), gridBuilder(), gameBuilder(), playerBuilder())

    @Bean
    fun shipPlacer(): ShipPlacer = StandardShipPlacer(battleshipGameCliAdapter())

    @Bean
    fun gridBuilder(): GridBuilder = StandardGridBuilder()

    @Bean
    fun shipBuilder(): ShipBuilder = StandardShipBuilder()

    @Bean
    fun gameBuilder(): GameBuilder = StandardGameBuilder()

    @Bean
    fun cliAdapter(): SystemCLIAdapter = StandardSystemCLIAdapter()

    @Bean
    fun playerBuilder(): PlayerBuilder = StandardPlayerBuilder()

    @Bean
    fun clearScreen(): ClearScreen = UnixClearScreen(cliAdapter())
}