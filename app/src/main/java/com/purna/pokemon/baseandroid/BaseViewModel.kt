package com.purna.pokemon.baseandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.purna.base.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application, protected val dispatchers: Dispatchers) :
    AndroidViewModel(application), CoroutineScope {
    private val parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = parentJob + dispatchers.ioDispatcher

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel(CancellationException("Parent Scope Destroyed"))
    }
}