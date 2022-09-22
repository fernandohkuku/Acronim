package com.fernando.acronym.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.entities.LfEntity
import com.fernando.acronym.databinding.ItemAcronymBinding

class HomeViewHolder(
    private val binding: ItemAcronymBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): HomeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = ItemAcronymBinding.inflate(inflater, parent, false)
            return HomeViewHolder(view)
        }
    }

    fun bindTo(acronymEntity: LfEntity) = with(binding) {
        acronym = acronymEntity
        executePendingBindings()
    }
}