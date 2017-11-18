package com.ddubson.battleship.game

interface Subscribable {
    fun subscribe(subscriber: Subscriber)
}