package com.marvel.app.presentation.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.marvel.app.R
import com.marvel.app.common.base.BaseFragment
import com.marvel.app.databinding.FragmentCharactersListBinding
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.presentation.characters.adapter.CharactersAdapter
import com.marvel.app.presentation.characters.adapter.CharactersAdapter.OnItemClickListener
import com.marvel.app.presentation.characters.adapter.MarvelLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersListFragment : BaseFragment<FragmentCharactersListBinding>(), OnItemClickListener {

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter


    override fun getViewBinding(): FragmentCharactersListBinding =
        FragmentCharactersListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharactersObserver()
        bindView()
        handleObservable()
    }

    private fun bindView() {
        adapter = CharactersAdapter(this)
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MarvelLoadStateAdapter { adapter.retry() },
            footer = MarvelLoadStateAdapter { adapter.retry() })
    }

    private fun handleObservable() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characters.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collectLatest { loadState ->
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            binding.containerLoading.containerLoading.visibility = View.VISIBLE
                        }
                        is LoadState.Error -> {
                            binding.containerLoading.containerLoading.visibility = View.GONE
                        }
                        is LoadState.NotLoading -> {
                            binding.containerLoading.containerLoading.visibility = View.GONE
                        }
                    }

                }
            }
        }
    }

    override fun onItemClick(charater: CharacterResult) {
//        val action = CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailsFragment(charater)
        val bundle = Bundle().apply {
            putParcelable("charater", charater) // For Parcelable
        }

        findNavController().navigate(R.id.action_charactersListFragment_to_characterDetailsFragment,bundle)


    }
}