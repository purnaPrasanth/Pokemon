package com.purna.pokemon.http.util

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/

const val defaultScheme = "https"

const val host = "www.google.com"

const val hostStartingWithScheme = "${defaultScheme}dgdygshs"

const val correctHttpUrl = "http://$host/"

const val correctHttpsUrl = "https://$host/"

const val incorrectScheme = "wws://$host/"

const val missingScheme = host

const val missingHost = "$defaultScheme://"

const val path = "prasanth"

const val query = "purna=prasanth"

val queryNamesAndValues = listOf("purna", "prasanth")

const val completeUrl = "$defaultScheme://$host/$path?$query"

const val completeUrlWithOutParams = "$defaultScheme://$host/$path"

const val completeUrlWithOutPath = "$defaultScheme://$host?$query"

