package com.example.studycards

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class CardSetAdapter(private val context: Context, private val data: List<CardSet>): RecyclerView.Adapter<CardSetAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val descView: TextView = view.findViewById(R.id.description)
        val button: Button = view.findViewById(R.id.button)
        val card: MaterialCardView = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardsets, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.titleView.text = item.title
        holder.descView.text = item.desc
        holder.card.setOnClickListener {
            holder.card.setChecked(!holder.card.isChecked())
        }
        holder.button.setOnClickListener {
            val intent = Intent(context, PracticeActivity::class.java).apply {
                //TODO: save and store ID's for card sets
                putExtra("ID", 1)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}