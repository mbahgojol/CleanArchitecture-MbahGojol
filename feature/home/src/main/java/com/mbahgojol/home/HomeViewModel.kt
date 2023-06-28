package com.mbahgojol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbahgojol.domain.GetNews
import com.mbahgojol.domain.GetNewsParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNews: GetNews,
) : ViewModel() {
    fun getNews() {
        viewModelScope.launch {
            getNews(GetNewsParams())
                .collect()
        }
    }
}