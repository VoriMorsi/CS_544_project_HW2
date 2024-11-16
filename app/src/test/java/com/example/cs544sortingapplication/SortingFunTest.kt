package com.example.cs544sortingapplication

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class SortingFunTest {
    @Test
    fun intermediateInsertionSort_elements() {
        val x = MainActivity.intermediateInsertionSort(intArrayOf(6, 4, 3), 1)
        assertArrayEquals(intArrayOf(4, 6, 3), x)
    }

    @Test
    fun testIntermediateInsertionSort() {
        // Test Case 1: Intermediate step of insertion sort
        val inputArray = intArrayOf(5, 3, 8, 6)
        val stepIndex = 2
        val expectedOutput = intArrayOf(3, 5, 8, 6) // At step 2, 8 is already in place

        val actualOutput = MainActivity.intermediateInsertionSort(inputArray, stepIndex)

        // Verify the output
        assertArrayEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testIntermediateInsertionSortWithSortedArray() {
        // Test Case 2: Already sorted array
        val inputArray = intArrayOf(1, 2, 3, 4)
        val stepIndex = 3
        val expectedOutput = intArrayOf(1, 2, 3, 4)

        val actualOutput = MainActivity.intermediateInsertionSort(inputArray, stepIndex)

        // Verify the output
        assertArrayEquals(expectedOutput, actualOutput)
    }


    @Test
    fun testIntermediateInsertionSortWithSingleElement() {
        // Test Case 4: Array with a single element
        val inputArray = intArrayOf(1)
        val stepIndex = 0
        val expectedOutput = intArrayOf(1)

        val actualOutput = MainActivity.intermediateInsertionSort(inputArray, stepIndex)

        // Verify the output
        assertArrayEquals(expectedOutput, actualOutput)
    }
}
