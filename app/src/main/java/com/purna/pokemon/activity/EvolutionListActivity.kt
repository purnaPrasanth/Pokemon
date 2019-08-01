package com.purna.pokemon.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.purna.pokemon.R
import com.purna.pokemon.adapter.EvolutionItemAdapter
import com.purna.pokemon.baseandroid.BaseActivity
import com.purna.pokemon.databinding.ActivityEvolutionListBinding
import com.purna.pokemon.http.VerticalSpaceDecorator
import com.purna.pokemon.instances.DataInstances
import com.purna.pokemon.viewmodel.EvolutionViewModel
import com.purna.pokemon.viewmodel.EvolutionViewModelFactory

class EvolutionListActivity : BaseActivity<ActivityEvolutionListBinding>(R.layout.activity_evolution_list),
    AdapterView.OnItemClickListener {

    private val evolutionUrl: String by lazy { intent.getStringExtra(BUNDLE_EVOLUTION_URL) }

    private val viewModel: EvolutionViewModel by lazy {
        ViewModelProviders.of(
            this,
            EvolutionViewModelFactory(
                application,
                evolutionUrl,
                DataInstances.pokemonRepo
            )
        ).get(EvolutionViewModel::class.java)
    }

    private val adapter: EvolutionItemAdapter by lazy { EvolutionItemAdapter(this) }

    override fun initUI() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(VerticalSpaceDecorator(12))
        viewModel.currentEvolutionLevel.observe(this, Observer {
            adapter.submitList(it)
        })
        adapter.onItemClickListener = this
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item = viewModel.currentEvolutionLevel.value?.get(p2)!!
        if (!viewModel.pokemonSelected(item.species.name)) {
            Toast.makeText(
                this,
                "Pokemon Reached Highest Evolution",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onBackPressed() {
        if (!viewModel.canGoBackInEvolution()) super.onBackPressed()
    }

    companion object {
        val BUNDLE_EVOLUTION_URL = "BUNDLE_EVOLUTION_URL"

        fun getIntent(context: Context, evolutionUrl: String): Intent {
            return Intent(context, EvolutionListActivity::class.java).apply {
                putExtra(BUNDLE_EVOLUTION_URL, evolutionUrl)
            }
        }

        fun startActivity(context: Context, evolutionUrl: String) {
            context.startActivity(getIntent(context, evolutionUrl))
        }
    }
}
