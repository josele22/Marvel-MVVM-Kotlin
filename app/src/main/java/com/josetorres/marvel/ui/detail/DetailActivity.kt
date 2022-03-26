package com.josetorres.marvel.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.josetorres.marvel.databinding.ActivityDetailCharacterBinding
import com.josetorres.marvel.loadUrl
import com.josetorres.marvel.showOrHidden
import com.josetorres.marvel.toast
import com.josetorres.marvel.ui.main.MainActivity


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCharacterBinding

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val mIntent = intent

        val idCharacterSelected = mIntent.getStringExtra(MainActivity.ID_CHARACTER_SELECTED)

        viewModel.state.observe(this@DetailActivity) { state ->

            state.character?.let {

                binding.tvTitleCharacterDetail.text = state.character.name

                binding.tvDescriptionCharacterDetail.text = state.character.description

                binding.ivCharacterDetail.loadUrl(state.character.thumbnail!!.path + "." + state.character.thumbnail!!.extension)

            } ?: run {

                state.error?.let {
                    toast(it)
                }

                finish()
            }


        }

        viewModel.progressVisible.observe(this) {
            binding.pbDetailScreen.showOrHidden(it)
        }

        viewModel.loadCharacterDetail(idCharacterSelected)

    }

}