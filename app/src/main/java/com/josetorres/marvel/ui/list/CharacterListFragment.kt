package com.josetorres.marvel.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.josetorres.marvel.MainActivity
import com.josetorres.marvel.R
import com.josetorres.marvel.databinding.FragmentCharacterListBinding
import com.josetorres.marvel.domain.model.CharacterDomain
import com.josetorres.marvel.showOrHidden
import com.josetorres.marvel.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterListFragment : Fragment() {

    companion object {
        const val ID_CHARACTER_SELECTED = "SELECTED_CHARACTER"
    }

    private lateinit var binding: FragmentCharacterListBinding

    private lateinit var adapterCharacters: CharacterAdapter

    val viewModelMainActivity: CharacterListViewModel by viewModel()

    var charactersList = ArrayList<CharacterDomain>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (!::binding.isInitialized) {

            binding = FragmentCharacterListBinding.inflate(inflater, container, false);

            adapterCharacters = CharacterAdapter(charactersList) {

                val bundle = Bundle()
                bundle.putString(ID_CHARACTER_SELECTED, it.id.toString())

                binding.root.findNavController().navigate(R.id.action_characterListFragment_to_detailFragment,bundle)

            }

            binding.rvMain.adapter = adapterCharacters

            binding.rvMain.layoutManager = GridLayoutManager(activity, 2)

            viewModelMainActivity.state.observe(viewLifecycleOwner) { state ->

                if (!state.list.isNullOrEmpty()) {
                    charactersList.clear()
                    charactersList.addAll(state.list)
                    adapterCharacters.notifyDataSetChanged()
                } else {
                    state.error?.let {
                        requireContext().toast(it)
                    }
                }

            }

            viewModelMainActivity.progressVisible.observe(viewLifecycleOwner) {
                binding.pbMainScreen.showOrHidden(it)
            }

            viewModelMainActivity.loadCharacters()
        }


        return binding.root;

    }
}