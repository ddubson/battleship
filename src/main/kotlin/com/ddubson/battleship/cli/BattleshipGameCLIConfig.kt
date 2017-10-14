package com.ddubson.battleship.cli

import com.ddubson.battleship.game.BattleshipGameEngine
import com.ddubson.battleship.game.BattleshipGameUiAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BattleshipGameCLIConfig {
    @Bean
    fun gameEngine(): BattleshipGameEngine = BattleshipGameEngine(battleshipGameUiAdapter())

    @Bean
    fun battleshipGameUiAdapter(): BattleshipGameUiAdapter = BattleshipGameCLI()
}