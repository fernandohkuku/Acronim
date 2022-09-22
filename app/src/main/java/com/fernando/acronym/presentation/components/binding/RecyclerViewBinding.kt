package com.fernando.acronym.presentation.components.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.entities.LfEntity
import com.fernando.acronym.presentation.ui.home.HomeAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("app:submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<LfEntity>?) {
        if (view.adapter != null && itemList !=null) {
            (view.adapter as HomeAdapter).addAcronyms(itemList)
        }
    }
}