package com.ddubson.battleship.game.core.cell

enum class TargetCellStatus {
    OPEN,
    HIT,
    MISS
}

enum class OceanCellStatus {
    OPEN,
    ENGAGED,
    HIT
}

enum class AttackStatus {
    MISS,
    HIT
}