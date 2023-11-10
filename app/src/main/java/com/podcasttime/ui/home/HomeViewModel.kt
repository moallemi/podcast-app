package com.podcasttime.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podcasttime.data.PodcastsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeRepository: PodcastsRepository,
) : ViewModel() {

  private val _state = MutableStateFlow(HomeUiState())
  val state = _state.asStateFlow()

  init {
    load()
  }

  private fun load() {
    viewModelScope.launch {
      _state.update { it.copy(isLoading = true) }
      val result = homeRepository.getPodcastCatalog()
      _state.update { homeUiState ->
        homeUiState.copy(
          isLoading = false,
          podcasts = result,
          categories = result.map { it.category },
        )
      }
    }
  }
}
