package com.purna.pokemon.http

import com.purna.pokemon.IBaseTest
import com.purna.pokemon.http.HttpUrl
import com.purna.pokemon.http.util.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.MalformedURLException

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/


class HttpUrlTest : IBaseTest {
    override fun before() {

    }

    override fun after() {

    }

    @Test
    fun testCorrectHttpUrl() {
        val httpUrl = HttpUrl.parseUrl(correctHttpUrl)

        assertEquals(httpUrl.scheme, "http")
        assertEquals(httpUrl.host, host)
    }

    @Test
    fun testCorrectHttpsUrl() {
        val httpUrl = HttpUrl.parseUrl(correctHttpsUrl)

        assertEquals(httpUrl.scheme, "https")
        assertEquals(httpUrl.host, host)
    }

    @Test
    fun testIncorrectScheme() {
        try {
            HttpUrl.parseUrl(incorrectScheme)
        } catch (exception: MalformedURLException) {
            assert(true)
            return
        }
        assert(false)
    }

    @Test
    fun testMissingHost() {
        try {
            HttpUrl.parseUrl(missingHost)
        } catch (exception: MalformedURLException) {
            assert(true)
            return
        }
        assert(false)
    }

    @Test
    fun testMissingScheme() {
        try {
            HttpUrl.parseUrl(missingScheme)
        } catch (exception: MalformedURLException) {
            assert(true)
            return
        }
        assert(false)
    }

    @Test
    fun testUnParsing() {
        val url = HttpUrl(
            scheme = defaultScheme,
            host = host,
            pathSegments = listOf(path),
            queriesAndNames = queryNamesAndValues
        ).toString()

        assertEquals(completeUrl, url)
    }

    @Test
    fun testHostStartingWithScheme() {
        try {
            HttpUrl.parseUrl(hostStartingWithScheme)
        } catch (exception: MalformedURLException) {
            assert(true)
            return
        }
        assert(false)
    }

    @Test
    fun testUpParsingWithOutParams() {
        val url = HttpUrl(
            scheme = defaultScheme,
            host = host,
            pathSegments = listOf(path)
        ).toString()

        assertEquals(completeUrlWithOutParams, url)
    }

    @Test
    fun testUpParsingWithOutPath() {
        val url = HttpUrl(
            scheme = defaultScheme,
            host = host,
            queriesAndNames = queryNamesAndValues
        ).toString()

        assertEquals(completeUrlWithOutPath, url)
    }
}