package com.purna.pokemon.http

import com.purna.pokemon.IBaseTest
import com.purna.pokemon.http.util.*
import com.purna.pokemon.util.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/
class UrlUtilTests : IBaseTest {
    override fun before() {

    }

    override fun after() {

    }

    @Test
    fun testSchemeDelimitter() {
        val index = schemeDelimiter(correctHttpsUrl)
        assertEquals(5, index)
    }

    @Test
    fun testHttpDelimitter() {
        val index = schemeDelimiter(correctHttpUrl)
        assertEquals(4, index)
    }

    @Test
    fun testRandomSchemeDelimitter() {
        val index = schemeDelimiter(incorrectScheme)
        assertEquals(3, index)
    }

    @Test
    fun testNoSlashes() {
        val sampleInput = "prasanth"
        assertEquals(0, slashCount(sampleInput, 0, sampleInput.length))
    }

    @Test
    fun testSlashCout() {
        val sampleInput = "pras///anth"
        assertEquals(3, slashCount(sampleInput, 4, sampleInput.length))
    }

    @Test
    fun testSlashCountWithCustomEnd() {
        val sampleInput = "pras///anth"
        assertEquals(1, slashCount(sampleInput, 4, 5))
    }

    @Test
    fun testDecodeQueryParams() {
        val outputQueriesAndNames = decodeQueryParams(query, 0, query.length)

        assertListsEqual(outputQueriesAndNames,
            queryNamesAndValues
        ) { firstListItem, secondListItem ->
            firstListItem == secondListItem
        }
    }

    @Test
    fun testNamesAndValuesToQueryString() {
        val outputQuery = namesAndValuesToQueryString(queryNamesAndValues)

        assertEquals(query, outputQuery)
    }

    @Test
    fun testDelimitterOffset() {
        val sampleInput = "prasanth"

        val output = delimiterOffset(sampleInput, 0, sampleInput.length, "sn")

        assertEquals(3, output)
    }
}