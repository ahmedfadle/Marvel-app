package com.marvel.app.presentation.characters

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marvel.app.common.model.IResult
import com.marvel.app.common.utils.SingleLiveEvent
import com.marvel.app.domain.models.CharacterResult
import com.marvel.app.domain.models.comics.ComicsResponse
import com.marvel.app.domain.usecases.GetCharactersUseCase
import com.marvel.app.domain.usecases.GetComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CharactersViewModel @Inject  constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getComicsUseCase: GetComicsUseCase
) : ViewModel() {


    private val _characters = MutableStateFlow<PagingData<CharacterResult>>(PagingData.empty())
    val characters: StateFlow<PagingData<CharacterResult>> get() = _characters


    private val _comicsiveData by lazy { SingleLiveEvent<IResult<ComicsResponse>>() }

    fun getComicesObservable()=_comicsiveData


    fun getCharactersObserver() {
        viewModelScope.launch {
            getCharactersUseCase.invoke().cachedIn(viewModelScope).collectLatest {
                _characters.emit(it)
            }
        }
    }


    fun getCharactersComicsObserver(charaterId:Int) {
        viewModelScope.launch {
            try {
            val response =  getComicsUseCase.invoke(charaterId)
                _comicsiveData.postValue(response)

            }catch (e:Exception){
                _comicsiveData.postValue(IResult.failure(e.localizedMessage))
            }
        }
    }












}