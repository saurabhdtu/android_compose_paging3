package com.sample.compose.ui.screen.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.sample.compose.data.localdb.entity.toBreedUiItem
import com.sample.compose.data.repository.BreedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(private val breedRepository: BreedRepository) : ViewModel(){

    val list = breedRepository.getPagingData().map { pagingData ->
            pagingData.map { it.toBreedUiItem()
        }
    }.cachedIn(scope = viewModelScope)

}