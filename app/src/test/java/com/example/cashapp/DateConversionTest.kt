package com.example.cashapp

import com.example.cashapp.presentation.util.toFormattedDateParts
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.TimeZone

class DateConversionTest {

    @Test
    fun test_valid_timestamp_conversion_local_zone() {

        //https://www.unixtimestamp.com/
        val timestamp = 1738391167L // Sat Feb 01 2025 17:26:07 - Australia
        TimeZone.setDefault(TimeZone.getTimeZone("Australia/Melbourne"))

        val (datePart, timePart) = timestamp.toFormattedDateParts()

        assertEquals("01 Feb 2025", datePart)
        assertEquals("05:26:07 pm", timePart)
    }

    @Test
    fun test_american_new_york_timezone() {
        val timestamp = 1738391167L   //Saturday February 01, 2025 01:26:07
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"))
        val (datePart, timePart) = timestamp.toFormattedDateParts()

        // Expected values for New York timezone
        assertEquals("01 Feb 2025", datePart)
        assertEquals("01:26:07 am", timePart)
    }

    @Test
    fun test_start_of_timestamp() {
        val timestamp = 0L
        val (datePart, timePart) = timestamp.toFormattedDateParts()

        // Expected values for invalid timestamp
        assertEquals("01 Jan 1970", datePart)
        assertEquals("10:00:00 am", timePart)
    }

    /*@Test
    fun test_negative_timestamp() {
        val timestamp = -1L
        val (datePart, timePart) = timestamp.toFormattedDateParts()

        // Expected values for invalid timestamp
        assertEquals("31 Dec 1969", datePart)
        assertEquals("00:00:00", timePart)
    }*/


    @Test
    fun test_leap_year_date() {
        val timestamp = 1582934400L // Saturday, 29 Feb 2020 11:00:00
        TimeZone.setDefault(TimeZone.getTimeZone("Australia/Melbourne"))
        val (datePart, timePart) = timestamp.toFormattedDateParts()

        // Expected values
        assertEquals("29 Feb 2020", datePart)
        assertEquals("11:00:00 am", timePart)
    }

}