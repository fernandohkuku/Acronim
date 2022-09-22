package com.fernando.acronym.presentation.components.validators

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.fernando.acronym.R
import com.fernando.acronym.ui_ktx.lifecycle.clear

internal class EntryValidator : Validator<String> {
    val liveError = MutableLiveData<Int?>()

    override fun isValid(input: String): Boolean = when{
        input.isBlank() -> {
            liveError.value = R.string.error_entry_empty
            false
        }
        else -> true
    }
    override fun reset()  = liveError.clear()
}