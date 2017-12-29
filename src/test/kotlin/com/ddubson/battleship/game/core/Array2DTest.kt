package com.ddubson.battleship.game.core

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class Array2DTest {
    @Test
    fun `set and get should perform their respective operation correctly`() {
        val size = 5
        val array = Array(size, { Array(size, { 0 }) })
        val array2D = Array2D(size, size, array)

        assertTrue(array2D.xSize == 5)
        assertTrue(array2D.ySize == 5)

        (0 until array2D.xSize).forEach { x ->
            (0 until array2D.ySize).forEach { y ->
                array2D[x, y] = 123
                assertThat(array2D[x, y], equalTo(123))
            }
        }

        array2D.forEach {
            assertThat(it, equalTo(123))
        }
    }
}