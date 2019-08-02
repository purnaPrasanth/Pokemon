package com.purna.pokemon.util

import org.junit.Assert

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/

fun <ACTUAL> assertListsEqual(
    list1: List<ACTUAL>,
    list2: List<ACTUAL>,
    comparator: (ACTUAL, ACTUAL) -> Boolean
) {
    if (list1.size != list2.size) assert(false)

    for (i in 0 until list1.size) {
        if (!comparator(list1[i], list2[i])) {
            assert(false)
            return
        }
    }

    assert(true)
}