package com.ddubson.battleship.game.core

interface Subscribable {
    fun subscribe(subscriber: Subscriber)
}