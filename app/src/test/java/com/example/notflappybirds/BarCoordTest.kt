package com.example.notflappybirds

import org.junit.Test

import org.junit.Assert.*

class BarCoordTest() {
    @Test
    fun BarCoordCalculatesCorrectBarCoords() {
        val horizontalPosition = 0F
        val gapSize = 100F
        val gapHeight = 300F


        val testBarCoord = BarCoord(horizontalPosition,gapSize, gapHeight)

        val barWidth = testBarCoord.barWidth

        assertEquals(horizontalPosition, testBarCoord.topBarLeft)
        assertEquals(horizontalPosition + barWidth, testBarCoord.topBarRight)
        assertEquals(0F, testBarCoord.topBarTop)
        assertEquals(gapHeight, testBarCoord.topBarBottom)

        assertEquals(horizontalPosition, testBarCoord.bottomBarLeft)
        assertEquals(horizontalPosition + barWidth, testBarCoord.bottomBarRight)
        assertEquals(gapHeight + gapSize, testBarCoord.bottomBarTop)
        assertEquals(4000F, testBarCoord.bottomBarBottom)
    }
}
