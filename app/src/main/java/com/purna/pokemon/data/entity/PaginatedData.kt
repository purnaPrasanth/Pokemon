package com.purna.pokemon.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A Generic Container for paginated data
 *
 * @param T the type of data that this container can hold
 * @param count total number of Items
 * @param next an url to fetch the next page
 * @param previous an url to fetch previous page
 * @param results the items of the current page
 */

@Serializable
data class PaginatedData<T>(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<T>
)