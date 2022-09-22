package com.fernando.acronym.presentation.components.binding

import androidx.databinding.BindingAdapter
import com.fernando.acronym.ui_ktx.content.getStringResources
import com.google.android.material.textfield.TextInputLayout


object EditTextViewBinding {
    @JvmStatic
    @BindingAdapter("app:setError")
    fun setError(textInputLayout: TextInputLayout, message: Int?) {
        textInputLayout.error = textInputLayout.context.getStringResources(message)
    }

}