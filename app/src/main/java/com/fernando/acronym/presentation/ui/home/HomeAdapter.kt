package com.fernando.acronym.presentation.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.entities.LfEntity
import com.fernando.acronym.ui_ktx.widgets.notifyChanged

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var acronyms: List<LfEntity> by notifyChanged(
        areContentsTheSame = { old, new -> old == new }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HomeViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeViewHolder) {
            val acronym = acronyms[position]
            holder.bindTo(acronym)
        }
    }

    override fun getItemCount(): Int = acronyms.size

    fun addAcronyms(acronyms: List<LfEntity>?) {
        this.acronyms = acronyms?: emptyList()
    }

}