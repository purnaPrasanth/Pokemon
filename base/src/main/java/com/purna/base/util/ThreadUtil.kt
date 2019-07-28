package com.purna.base.util

import java.util.concurrent.ThreadFactory

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

fun threadFactory(name: String, daemon: Boolean): ThreadFactory {
    return ThreadFactory { r ->
        val result = Thread(r, name)
        result.isDaemon = daemon
        result
    }
}