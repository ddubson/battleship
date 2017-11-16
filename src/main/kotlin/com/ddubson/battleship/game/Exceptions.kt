package com.ddubson.battleship.game

class ShipAlreadyPlacedException : RuntimeException()

class ShipOverlapsException: RuntimeException()

class CellAlreadyEngagedException: RuntimeException()

class ShipBeyondBoundsException: RuntimeException()