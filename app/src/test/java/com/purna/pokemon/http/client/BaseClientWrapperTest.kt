package com.purna.pokemon.http.client

import com.purna.pokemon.IBaseTest
import com.purna.pokemon.http.HttpClient
import com.purna.pokemon.httpwrapper.ClientWrapper
import com.purna.pokemon.util.ioDispatcher
import okhttp3.mockwebserver.MockWebServer

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/

open class BaseClientWrapperTest : IBaseTest {

    private val httpClient: HttpClient = HttpClient(
        dispatcher = ioDispatcher
    )

    val mockWebServer: MockWebServer = MockWebServer()

    protected val httpWrapper by lazy {
        ClientWrapper(
            httpClient
        )
    }

    override fun before() {
    }

    override fun after() {
        mockWebServer.shutdown()
    }
}