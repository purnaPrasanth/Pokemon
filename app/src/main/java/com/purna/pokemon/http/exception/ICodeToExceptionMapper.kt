package com.purna.pokemon.http.exception

/**
 * Mapper for Mapping Http Response Code to [HttpException]
 */

interface ICodeToExceptionMapper {
    fun mapCodeToException(code: Int): HttpException?
}