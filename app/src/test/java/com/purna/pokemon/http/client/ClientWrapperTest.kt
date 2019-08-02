package com.purna.pokemon.http.client

import com.purna.pokemon.data.entity.PaginatedData
import com.purna.pokemon.data.entity.PokemonListItem
import com.purna.pokemon.httpwrapper.exception.BadRequestException
import com.purna.pokemon.httpwrapper.exception.HttpException
import com.purna.pokemon.util.pokemonTestData
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.net.SocketTimeoutException

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/

class ClientWrapperTest : BaseClientWrapperTest() {

    @Test
    fun testSuccess() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(pokemonTestData))

        runBlocking {
            val url = mockWebServer.url("")
            httpWrapper.getResource(PaginatedData.serializer(PokemonListItem.serializer()), url.toString())
            assert(true)
        }
    }

    @Test
    fun testBadRequest() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody(pokemonTestData))

        runBlocking {
            val url = mockWebServer.url("")
            try {
                httpWrapper.getResource(PaginatedData.serializer(PokemonListItem.serializer()), url.toString())
            } catch (exception: HttpException) {
                assert(exception is BadRequestException)
            }
        }
    }

    @Test
    fun testSocketTimeOut() {
        mockWebServer.enqueue(
            MockResponse().setBody("ABC").setResponseCode(200).clearHeaders().addHeader("Content-Length: 4")
        )

        try {
            runBlocking {

                val url = mockWebServer.url("")
                httpWrapper.getResource(PaginatedData.serializer(PokemonListItem.serializer()), url.toString())
                assert(false)
            }
        } catch (exception: Exception) {
            assert(exception is SocketTimeoutException)

        }
    }
}