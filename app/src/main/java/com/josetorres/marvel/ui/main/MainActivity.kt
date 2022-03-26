package com.josetorres.marvel.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.josetorres.marvel.databinding.ActivityMainBinding
import com.josetorres.marvel.domain.model.CharacterDomain
import com.josetorres.marvel.showOrHidden
import com.josetorres.marvel.toast
import com.josetorres.marvel.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val ID_CHARACTER_SELECTED = "SELECTED_CHARACTER"
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapterCharacters: CharacterAdapter

    private val viewModel by viewModels<MainViewModel>()

    var charactersList = ArrayList<CharacterDomain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adapterCharacters = CharacterAdapter(charactersList) {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(Companion.ID_CHARACTER_SELECTED, it.id.toString())
            startActivity(intent)
        }

        binding.rvMain.adapter = adapterCharacters

        binding.rvMain.layoutManager = GridLayoutManager(this, 2)

        viewModel.state.observe(this@MainActivity) { state ->

            if (!state.list.isNullOrEmpty()) {
                charactersList.clear()
                charactersList.addAll(state.list)
                adapterCharacters.notifyDataSetChanged()
            } else {
                state.error?.let {
                    toast(it)
                }

                finish()
            }

        }

        viewModel.progressVisible.observe(this) {
            binding.pbMainScreen.showOrHidden(it)
        }

        viewModel.loadCharacters()
    }



}