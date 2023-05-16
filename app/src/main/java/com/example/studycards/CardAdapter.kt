package com.example.studycards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class CardAdapter(private val context: Context, private val data: List<Card>): RecyclerView.Adapter<CardAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val frontView: TextView = view.findViewById(R.id.front)
        val descView: TextView = view.findViewById(R.id.back)
        val card: MaterialCardView = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.frontView.text = item.front
        holder.descView.text = item.back
        holder.card.setOnClickListener {
            holder.card.setChecked(!holder.card.isChecked())
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}