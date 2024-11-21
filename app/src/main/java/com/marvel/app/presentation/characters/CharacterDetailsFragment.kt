package com.marvel.app.presentation.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.marvel.app.common.base.BaseFragment
import com.marvel.app.common.extentions.loadImage
import com.marvel.app.common.model.getData
import com.marvel.app.common.utils.Utils
import com.marvel.app.databinding.FragmentCharacterDetailBinding
import com.marvel.app.databinding.FragmentCharactersListBinding
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.domain.models.Item
import com.marvel.app.presentation.characters.adapter.CharactersAdapter
import com.marvel.app.presentation.characters.adapter.CharactersAdapter.OnItemClickListener
import com.marvel.app.presentation.characters.adapter.DetailsItemRecyAdapter
import com.marvel.app.presentation.characters.adapter.MarvelLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailBinding>() ,
    DetailsItemRecyAdapter.OnItemClickListener {

    private val viewModel: CharactersViewModel by viewModels()
//    private val args: CharacterDetailsFragmentArgs by navArgs()
    private var character: CharacterResult? = null

    private lateinit var comicsAdapter:DetailsItemRecyAdapter
    private lateinit var storiesAdapter:DetailsItemRecyAdapter
    private lateinit var eventAdapter:DetailsItemRecyAdapter
    private lateinit var seriesAdapter:DetailsItemRecyAdapter

    override fun getViewBinding(): FragmentCharacterDetailBinding =
        FragmentCharacterDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//         character = args.CharacterResult
         character = arguments?.getParcelable<CharacterResult>("charater")

        handleObservable()
        bindView()
    }

    private fun handleObservable() {
        viewModel.getComicesObservable().observe(viewLifecycleOwner) { result ->
            result.let {
                when {
                    it.isLoading() -> {
                    }
                    it.isSuccessful() -> {

                        if (it.getData() != null && !it.getData()?.data?.results.isNullOrEmpty()) {
                            binding.rvComics.visibility = View.VISIBLE
                            binding.tvComicsLable.visibility = View.VISIBLE

                            val comeicsList = it.getData()?.data?.results?.map {
                                Item(
                                    name = it.title,
                                    resourceURI = it.thumbnail?.path ?: "",
                                    null
                                )
                            }?.toCollection(ArrayList())
                            comicsAdapter = DetailsItemRecyAdapter(comeicsList!!, this)
                            binding.rvComics.adapter = comicsAdapter

                        } else {
                            binding.rvComics.visibility = View.GONE
                            binding.tvComicsLable.visibility = View.GONE
                        }
                    }

                    it.isFailed() -> {
                        binding.rvComics.visibility = View.GONE
                        binding.tvComicsLable.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun bindView() {

        binding.ivBack.setOnClickListener(){
            findNavController().popBackStack()
        }

        character?.let {
            viewModel.getCharactersComicsObserver(it.id!!)

           if (!it.description.isNullOrBlank()) {
                binding.tvDescValue.text = it.description
            }else {
                binding.tvDescValue.visibility =View.GONE
                binding.tvDescLable.visibility =View.GONE
            }
            it.name?.let {
                binding.tvNameValue.text = it
            }?: {
                binding.tvNameValue.visibility =View.GONE
                binding.tvNameLable.visibility =View.GONE
            }

            binding.ivCover.loadImage(Utils.getFullImagePath(it.thumbnail?.path?: "",it.thumbnail?.extension ?: ""))


            val eventList = it.events?.items?.toCollection(ArrayList())
            val seriesList = it.series?.items?.toCollection(ArrayList())
            val storieList = it.stories?.items?.toCollection(ArrayList())


            if (eventList.isNullOrEmpty()){
                binding.rvEvent.visibility =View.GONE
                binding.tvEventLable.visibility =View.GONE
            }else{
                eventAdapter = DetailsItemRecyAdapter(eventList , this)
                binding.rvEvent.adapter = eventAdapter
            }


            if (seriesList.isNullOrEmpty()){
                binding.rvSeries.visibility =View.GONE
                binding.tvSeriesLable.visibility =View.GONE
            }else{
                seriesAdapter = DetailsItemRecyAdapter(seriesList , this)
                binding.rvSeries.adapter = seriesAdapter
            }

            if (storieList.isNullOrEmpty()){
                binding.rvStories.visibility =View.GONE
                binding.tvStoriesLable.visibility =View.GONE
            }else{
                storiesAdapter = DetailsItemRecyAdapter(storieList , this)
                binding.rvStories.adapter = storiesAdapter
            }

        }

    }

    override fun onItemClick(item: Item) {

        val bundle = Bundle()
        bundle.putParcelable(DetailsDialogPopFragment.Item_KEY,item)
        var DdtailsDialogPopFragment = DetailsDialogPopFragment()
        DdtailsDialogPopFragment.arguments=bundle
        DdtailsDialogPopFragment.show(requireActivity().supportFragmentManager, "DetailsDialogPopFragment")

    }


}