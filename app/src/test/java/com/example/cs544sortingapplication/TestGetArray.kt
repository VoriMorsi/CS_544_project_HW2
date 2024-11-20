package com.example.cs544sortingapplication

import org.junit.Assert.*
import org.junit.Test

class MainActivityTest {

    @Test
    //Test a valid array
    fun testValidInput() {
        val input = "1 2 3"
        val result = MainActivity.getArrayFromString(input)
        assertNotNull(result.array)
        assertArrayEquals(intArrayOf(1, 2, 3), result.array)
        assertEquals(MainActivity.VALID_ARRAY_TEXT, result.errorMessage)
    }

    @Test
    //Test a non digit element
    fun testInvalidCharacterInput() {
        val input = "1 2 a"
        val result = MainActivity.getArrayFromString(input)
        assertNull(result.array)
        assertEquals(MainActivity.INVALID_ELEMENT_ERROR_TEXT, result.errorMessage)
    }

    @Test
    //Test less than 3 elements
    fun testTooFewElements() {
        val input = "1 2"
        val result = MainActivity.getArrayFromString(input)
        assertNull(result.array)
        assertEquals(MainActivity.ARRAY_TOO_SMALL_ERROR_TEXT, result.errorMessage)
    }

    @Test
    //Test more than 8 elements
    fun testTooManyElements() {
        val input = "1 2 3 4 5 6 7 8 9"
        val result = MainActivity.getArrayFromString(input)
        assertNull(result.array)
        assertEquals(MainActivity.ARRAY_TOO_LARGE_ERROR_TEXT, result.errorMessage)
    }

    @Test
    //Test null case
    fun testEmptyInput() {
        val input = ""
        val result = MainActivity.getArrayFromString(input)
        assertNull(result.array)
        assertEquals(MainActivity.ARRAY_TOO_SMALL_ERROR_TEXT, result.errorMessage)
    }
}