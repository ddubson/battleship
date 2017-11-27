package com.ddubson.battleship.game.core

class ShipAlreadyPlacedException : RuntimeException()

class ShipOverlapsException: RuntimeException()

class CellAlreadyEngagedException: RuntimeException()

class ShipBeyondBoundsException: RuntimeException()

class InvalidInputException: RuntimeException()