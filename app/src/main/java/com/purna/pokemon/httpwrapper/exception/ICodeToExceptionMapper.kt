package com.purna.pokemon.httpwrapper.exception

/**
 * Mapper for Mapping Http Response Code to [HttpException]
 */

interface ICodeToExceptionMapper {
    fun mapCodeToException(code: Int): HttpException?
}