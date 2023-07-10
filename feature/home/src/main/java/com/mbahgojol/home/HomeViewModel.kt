package com.mbahgojol.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbahgojol.domain.GetNews
import com.mbahgojol.domain.GetNewsParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNews: GetNews,
) : ViewModel() {
    fun getNews() {
        viewModelScope.launch {
            getNews(GetNewsParams())
                .fold(
                    onSuccess = {
                        Timber.e("Success")
                    },
                    onFailure = {
                        Timber.d("Success")
                    },
                )
        }
    }
}
