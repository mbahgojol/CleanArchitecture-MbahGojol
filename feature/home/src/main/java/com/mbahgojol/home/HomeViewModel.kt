package com.mbahgojol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbahgojol.common.state.UiState
import com.mbahgojol.common.state.mergeWithLoading
import com.mbahgojol.common.state.setValue
import com.mbahgojol.domain.GetNews
import com.mbahgojol.domain.GetNewsParams
import com.mbahgojol.model.dtos.ResponseNewsDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNews: GetNews,
) : ViewModel() {

    private val _newsListState = MutableStateFlow(UiState<ResponseNewsDto>())
    val newsListState
        get() = _newsListState.mergeWithLoading(getNews.inProgress)

    fun getNews() {
        viewModelScope.launch {
            val result = getNews(GetNewsParams())
            _newsListState.setValue(result)
        }
    }
}
