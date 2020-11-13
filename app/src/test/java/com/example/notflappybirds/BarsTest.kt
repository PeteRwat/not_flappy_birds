package com.example.notflappybirds

import org.junit.Test

import org.junit.Assert.*

class BarsTest {
    @Test
    fun toDrawGetsInitialisedWith3Bars(){
        val bars = Bars()

        assertEquals(bars.toDraw.size, 3)
    }
}