package com.purna.pokemon.instances

import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.pokemon.http.HttpClient
import com.purna.pokemon.httpwrapper.ClientWrapper
import java.util.concurrent.TimeUnit

/**
 * Container for Network Layer Instances
 */

object NetworkInstances {
    private val httpClientGenerator: BaseGenerator<HttpClient> = single {
        HttpClient(
            dispatcher = ExecutorInstances.ioDispatcher,
            timeUnit = TimeUnit.SECONDS,
            readTimeOut = 20,
            connectTimeOut = 20
        )
    }

    private val clientWrapperGenerator: BaseGenerator<ClientWrapper> = single {
        ClientWrapper(httpClient)
    }

    val httpClient: HttpClient
        get() = httpClientGenerator.getInstance()

    val clientWrapper: ClientWrapper
        get() = clientWrapperGenerator.getInstance()
}