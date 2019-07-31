package com.purna.pokemon.baseandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = parentJob

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel(CancellationException("Parent Scope Destroyed"))
    }
}