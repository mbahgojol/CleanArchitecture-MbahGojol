package com.mbahgojol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbahgojol.common.interactor.invoke
import com.mbahgojol.common.state.UiState
import com.mbahgojol.common.state.setValue
import com.mbahgojol.common.state.withLoading
import com.mbahgojol.domain.GetNews
import com.mbahgojol.model.entities.ArticleEntities
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNews: GetNews,
) : ViewModel() {

    private val _newsListState = MutableStateFlow(UiState<List<ArticleEntities>>())
    val newsListState
        get() = _newsListState.withLoading(getNews.inProgress)

    fun fetchNews() {
        viewModelScope.launch {
            val result = getNews.invoke()
            _newsListState.setValue(result)
        }
    }
}
