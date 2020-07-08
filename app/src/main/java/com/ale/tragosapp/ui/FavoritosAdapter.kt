package com.ale.tragosapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ale.tragosapp.R
import com.ale.tragosapp.base.BaseViewHolder
import com.ale.tragosapp.data.model.Drink
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.favoritos_row.view.*
import kotlinx.android.synthetic.main.tragos_row.view.*

class FavoritosAdapter(private val context: Context, private val tragosList: List<Drink>,
                  private val itemClickLister:OnTragoClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTragoClickListener{
        fun onTragoClick(drink: Drink)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.favoritos_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemView.imagen_favoritas)
            itemView.nombre_favoritas.text = item.nombre
            itemView.setOnClickListener { itemClickLister.onTragoClick(item) }
        }
    }
}