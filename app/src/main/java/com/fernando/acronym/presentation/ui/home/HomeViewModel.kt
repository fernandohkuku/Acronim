package com.fernando.acronym.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.entities.LfEntity
import com.fernando.acronym.core.domain.usecases.acronym.GetAcronymsUseCase
import com.fernando.acronym.presentation.components.validators.EntryValidator
import com.fernando.acronym.ui_ktx.lifecycle.clear
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getAcronymsUseCase: GetAcronymsUseCase,
) : ViewModel() {

    private val mLiveError = MutableLiveData<String>()
    val liveError = mLiveError

    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading = mIsLoading

    private val mAcronyms = MutableLiveData<List<LfEntity>>()
    val acronyms = mAcronyms

    val sf = MutableLiveData<String>("")

    val entry = MutableLiveData("")

    val entryValidator = EntryValidator()

    fun onGetAcronyms(entry: String) = viewModelScope.launch(Main) {
        entryValidator.reset()
        if (!entryValidator.isValid(entry)) {
            return@launch
        }
        startLoading()
        getAcronymsUseCase(entry).fold(::onSuccess, ::onError)
    }

    private fun onSuccess(acronyms: List<AcronymEntity>) {
        if (acronyms.isNotEmpty()) {
            mAcronyms.value = acronyms.first().lfs
            sf.value = acronyms.first().sf
        } else {
            mAcronyms.value = emptyList()
            sf.value = null
        }
        stopLoading()
    }

    private fun startLoading() {
        mIsLoading.value = true
    }

    private fun stopLoading() {
        mIsLoading.value = false
    }

    private fun onError(exception: Exception) {
        mLiveError.value = exception.message
        stopLoading()
        Timber.e(exception)
    }


}