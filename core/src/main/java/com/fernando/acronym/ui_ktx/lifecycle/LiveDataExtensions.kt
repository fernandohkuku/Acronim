package com.fernando.acronym.ui_ktx.lifecycle

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.clear() {
    this.value = null
}