package com.josetorres.marvel.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.josetorres.marvel.R
import com.josetorres.marvel.databinding.FragmentDetailCharacterBinding
import com.josetorres.marvel.loadUrl
import com.josetorres.marvel.showOrHidden
import com.josetorres.marvel.toast
import com.josetorres.marvel.ui.list.CharacterListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailCharacterBinding

    private val viewModelDetail: DetailViewModel by viewModel()

    var idSelectedCharacter: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idSelectedCharacter =requireArguments().getString(CharacterListFragment.ID_CHARACTER_SELECTED)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailCharacterBinding.inflate(inflater, container, false);

        if (idSelectedCharacter != null) {

            val idSelectedCharacter =
                requireArguments().getString(CharacterListFragment.ID_CHARACTER_SELECTED)

            viewModelDetail.state.observe(viewLifecycleOwner) { state ->

                state.character?.let {

                    binding.tvTitleCharacterDetail.text = state.character.name

                    binding.tvDescriptionCharacterDetail.text = state.character.description

                    binding.ivCharacterDetail.loadUrl(state.character.thumbnail!!.path + "." + state.character.thumbnail!!.extension)

                } ?: run {

                    state.error?.let {
                        requireContext().toast(it)
                    }

                    binding.root.findNavController().popBackStack()
                }

            }

            viewModelDetail.progressVisible.observe(viewLifecycleOwner) {
                binding.pbDetailScreen.showOrHidden(it)
            }

            viewModelDetail.loadCharacterDetail(idSelectedCharacter)

        } else {

            requireContext().toast(requireContext().resources.getString(R.string.character_detail_selected_error))

            requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()

        }

        return binding.root;

    }

}