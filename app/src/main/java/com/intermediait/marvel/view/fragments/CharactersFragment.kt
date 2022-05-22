package com.intermediait.marvel.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.intermediait.marvel.databinding.FragmentCharactersBinding
import com.intermediait.marvel.presentation.CharactersViewModel
import com.intermediait.marvel.view.adapter.CharacterComparator
import com.intermediait.marvel.view.adapter.CharactersAdapter
import com.intermediait.marvel.view.adapter.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()
    private val charactersPagingAdapter = CharactersAdapter(CharacterComparator)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        with(charactersPagingAdapter) {
            binding.rvListCharacters.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
        }
        charactersPagingAdapter.onCharacterClickListener = { character ->
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
                    character)
            findNavController().navigate(action)
        }
        fetchCharacters()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            charactersPagingAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    private fun fetchCharacters() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacters().collectLatest {
                charactersPagingAdapter.submitData(it)
            }
        }
    }


}