package com.intermediait.marvel.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.intermediait.marvel.databinding.FragmentCharacterDetailBinding
import com.intermediait.marvel.presentation.CharacterDetailViewModel
import com.intermediait.marvel.view.BindingHelper
import com.intermediait.marvel.view.activities.AppActivity
import com.intermediait.marvel.view.adapter.ComicsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        viewModel.setCharacter(args.character)
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val fixedLinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.rvComics.layoutManager = fixedLinearLayoutManager
        (activity as AppActivity).setToolbarTitle(args.character.name)
        (activity as AppActivity).setCloseIcon()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.character.observe(viewLifecycleOwner) {
            binding.rvComics.adapter = ComicsAdapter(it.comics)
            if(it.description.isEmpty()){
                binding.txtDescription.visibility = View.GONE
            }else{
                binding.txtDescription.text = it.description
            }
            BindingHelper.loadImage(binding.imgCharacter, it.thumbnail)
        }
    }

}