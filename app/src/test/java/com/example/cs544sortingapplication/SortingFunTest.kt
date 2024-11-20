package com.example.cs544sortingapplication

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class SortingFunTest {
    @Test
    // Test one step sort
    fun intermediateInsertionSort_elements() {
        val x = MainActivity.intermediateInsertionSort(intArrayOf(6, 4, 3), 1)
        assertArrayEquals(intArrayOf(4, 6, 3), x)
    }

    @Test
    // Test a certain digit in step 3 during sorting
    fun testIntermediateInsertionSort() {
        val inputArray = intArrayOf(5, 3, 8, 6)
        val stepIndex = 2
        val expectedOutput = intArrayOf(3, 5, 8, 6)
        val actualOutput = MainActivity.intermediateInsertionSort(inputArray, stepIndex)
        assertArrayEquals(expectedOutput, actualOutput)
    }

    @Test
    // Test a certain digit in step 4 during sorting
    fun testIntermediateInsertionSortWithSortedArray() {
        val inputArray = intArrayOf(1, 2, 3, 4)
        val stepIndex = 3
        val expectedOutput = intArrayOf(1, 2, 3, 4)
        val actualOutput = MainActivity.intermediateInsertionSort(inputArray, stepIndex)
        assertArrayEquals(expectedOutput, actualOutput)
    }

    @Test
    // Test an array with 1 digit
    fun testIntermediateInsertionSortWithSingleElement() {
        val inputArray = intArrayOf(1)
        val stepIndex = 0
        val expectedOutput = intArrayOf(1)
        val actualOutput = MainActivity.intermediateInsertionSort(inputArray, stepIndex)
        assertArrayEquals(expectedOutput, actualOutput)
    }
}
