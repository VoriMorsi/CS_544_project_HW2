package com.example.cs544sortingapplication

import org.junit.Test
import org.junit.Assert.*


class ExampleUnitTest {
    @Test
    fun test_getArrayFromString_Simple() {
        val x = MainActivity.getArrayFromString("1 2 3")
        assertArrayEquals(intArrayOf(1, 2, 3), x.array)
    }

    @Test
    fun test_intermediateInsertionSort_Simple() {
        val x = MainActivity.intermediateInsertionSort(intArrayOf(3, 2, 1), 1)
        assertArrayEquals(intArrayOf(2, 3, 1), x)
    }
}