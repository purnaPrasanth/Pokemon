package com.purna.pokemon.baseandroid

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.purna.pokemon.instances.ExecutorInstances
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<BINDING : ViewDataBinding>(@LayoutRes val layoutId: Int) : AppCompatActivity(),
    CoroutineScope {
    protected lateinit var binding: BINDING

    private val parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = ExecutorInstances.mainDispatcher + parentJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        initUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        parentJob.cancel(cause = CancellationException("Parent Scope Destroyed"))
    }

    abstract fun initUI()
}