package com.purna.pokemon.instances

import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.pokemon.http.ClientWrapper
import com.purna.pokemon.http.HttpClient

object NetworkInstances {
    private val httpClientGenerator: BaseGenerator<HttpClient> = single {
        HttpClient(
            dispatcher = ExecutorInstances.ioDispatcher
        )
    }

    private val clientWrapperGenerator: BaseGenerator<ClientWrapper> = single {
        ClientWrapper(
            httpClient,
            ExecutorInstances.ioDispatcher
        )
    }

    val httpClient: HttpClient
        get() = httpClientGenerator.getInstance()

    val clientWrapper: ClientWrapper
        get() = clientWrapperGenerator.getInstance()
}