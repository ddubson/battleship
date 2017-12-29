package com.ddubson.battleship.game.core

class Array2D<T>(val xSize: Int, val ySize: Int, val array: Array<Array<T>>) {
    operator fun get(x: Int, y: Int): T {
        return array[x][y]
    }

    operator fun set(x: Int, y: Int, t: T) {
        array[x][y] = t
    }

    inline fun forEach(operation: (T) -> Unit) {
        array.forEach { it.forEach { operation.invoke(it) } }
    }
}