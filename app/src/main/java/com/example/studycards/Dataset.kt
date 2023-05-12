package com.example.studycards

import android.content.Context

class Dataset {
    fun loadData(context: Context): List<CardSet> {
        val list = mutableListOf<CardSet>()
        val pref = context.getSharedPreferences("sets", Context.MODE_PRIVATE)
        val cards = pref.all
        var i = 0
        val keys = cards.keys
        while (i < cards.size-1) {
            list.add(CardSet(keys.elementAt(i), pref.getString(keys.elementAt(i), "")!!))
            i++
        }

        return list.toList()
    }
}