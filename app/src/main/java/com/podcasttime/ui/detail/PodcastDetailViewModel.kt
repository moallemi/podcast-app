package com.podcasttime.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podcasttime.data.PodcastsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastDetailViewModel @Inject constructor(
  private val podcastsRepository: PodcastsRepository,
) : ViewModel() {

  private val _state = MutableStateFlow(PodcastDetailUiState())
  val state = _state.asStateFlow()

  fun loadPodcast(id: String) = viewModelScope.launch {
    _state.value = _state.value.copy(isLoading = true)
    delay(1_000)
    podcastsRepository.getPodcast(id)
      .also { podcastDetail ->
        _state.value = _state.value.copy(
          isLoading = false,
          podcastDetail = podcastDetail,
        )
      }

    _state.value = _state.value.copy(isLoadingEpisode = true)
    delay(1_000)
    podcastsRepository.getEpisodeList(id)
      .also { episodeList ->
        _state.value = _state.value.copy(
          isLoadingEpisode = false,
          episodeList = episodeList,
        )
      }
  }
}
