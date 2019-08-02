package com.purna.pokemon

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/

@RunWith(JUnit4::class)
interface IBaseTest {

    @Before
    fun before()

    @After
    fun after()
}