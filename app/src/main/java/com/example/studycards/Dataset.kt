package com.example.studycards

import android.content.Context
import android.util.Log

class Dataset {
    fun loadData(context: Context): List<CardSet> {
        Log.d("Dataset", "Starting to load card sets")
        val list = mutableListOf<CardSet>()
        val pref = context.getSharedPreferences("sets", Context.MODE_PRIVATE)
        val cards = pref.all
        var i = 0
        val keys = cards.keys
        Log.d("Dataset", "Keys found: ${keys.joinToString()}")
        while (i < cards.size) {
            list.add(CardSet(keys.elementAt(i), pref.getString(keys.elementAt(i), "")!!))
            i++
        }
        Log.d("Dataset", "loaded data: ${list.joinToString()}")
        return list.toList()
    }
    fun loadCards(context: Context, id: String): List<Card> {
        Log.d("Dataset", "Starting to load cards for $id")
        val list = mutableListOf<Card>()
        val pref = context.getSharedPreferences(id, Context.MODE_PRIVATE)
        val cards = pref.all
        val keys = cards.keys
        Log.d("Dataset", "Keys found: ${keys.joinToString()}")
        var i = 0
        while (i < cards.size) {
            list.add(Card(keys.elementAt(i), pref.getString(keys.elementAt(i), "")!!))
            i++
        }
        Log.d("Dataset", "loaded data: ${list.joinToString()}")
        return list.toList()
    }
}