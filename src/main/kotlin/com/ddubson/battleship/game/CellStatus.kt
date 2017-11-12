package com.ddubson.battleship.game

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