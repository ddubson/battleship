package com.ddubson.battleship

import com.ddubson.battleship.cli.CLIUserInterfaceAdapter
import com.ddubson.battleship.cli.ClearScreen
import com.ddubson.battleship.cli.StandardSystemCLIAdapter
import com.ddubson.battleship.cli.SystemCLIAdapter
import com.ddubson.battleship.cli.unix.UnixClearScreen
import com.ddubson.battleship.game.core.BattleshipGameEngine
import com.ddubson.battleship.game.core.ShipPlacer
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.standard.StandardBattleshipGameEngine
import com.ddubson.battleship.game.standard.StandardShipPlacer
import com.ddubson.battleship.game.standard.adapters.StandardGameComponentAdapter
import com.ddubson.battleship.game.standard.builders.StandardGameBuilder
import com.ddubson.battleship.game.standard.builders.StandardGridBuilder
import com.ddubson.battleship.game.standard.builders.StandardPlayerBuilder
import com.ddubson.battleship.game.standard.builders.StandardShipBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BattleshipGameConfig {
    @Bean
    fun gameEngine(): BattleshipGameEngine = StandardBattleshipGameEngine(
            userInterfaceAdapter(), gameComponentAdapter(), shipBuilder(), shipPlacer())

    @Bean
    fun userInterfaceAdapter(): UserInterfaceAdapter = CLIUserInterfaceAdapter(cliAdapter(), clearScreen())

    @Bean
    fun gameComponentAdapter(): GameComponentAdapter = StandardGameComponentAdapter(
            userInterfaceAdapter(), gridBuilder(), gameBuilder(), playerBuilder())

    @Bean
    fun shipPlacer(): ShipPlacer = StandardShipPlacer(userInterfaceAdapter())

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