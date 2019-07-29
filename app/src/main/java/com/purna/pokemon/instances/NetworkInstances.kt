package com.purna.pokemon.instances

import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.pokemon.http.HttpClient
import java.util.concurrent.TimeUnit

object NetworkInstances {
    private val httpClientGenerator: BaseGenerator<HttpClient> = single {
        HttpClient(
            dispatcher = ExecutorInstances.ioDispatcher,
            readTimeOut = 15,
            connectTimeout = 15,
            timeUnit = TimeUnit.SECONDS
        )
    }

    val httpClient: HttpClient
        get() = httpClientGenerator.getInstance()
}