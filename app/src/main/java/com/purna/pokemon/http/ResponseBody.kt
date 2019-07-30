package com.purna.pokemon.http

import java.io.Reader

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/
abstract class ResponseBody(protected val reader: Reader) {
    abstract fun string(): String
}