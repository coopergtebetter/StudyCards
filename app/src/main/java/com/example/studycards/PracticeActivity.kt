package com.example.studycards

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.card.MaterialCardView

class PracticeActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var flashcards: MutableList<Card>
    private var currentIndex: Int=0
    private var isFront: Boolean = true

    private lateinit var cardView: MaterialCardView
    private lateinit var textView: TextView
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)
        flashcards = mutableListOf<Card>()
        getData()

        cardView = findViewById(R.id.cardView)
        textView = findViewById(R.id.textView)
        nextButton = findViewById(R.id.nextButton)
        prevButton = findViewById(R.id.prevButton)

        cardView.setOnClickListener{
            flipCard()
        }
        nextButton.setOnClickListener {
            showNextCard()
        }
        prevButton.setOnClickListener {
            showPreviousCard()
        }

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0)
            isFront = savedInstanceState.getBoolean("isFront", true)
        }
        showCard(currentIndex,isFront)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("currentIndex", currentIndex)
        outState.putBoolean("isFront", isFront)
    }

    fun getData() {
        if (!flashcards.isEmpty()) return
        val id = intent.getStringExtra("ID")
        sharedPreferences = getSharedPreferences(id, Context.MODE_PRIVATE)
        val data = sharedPreferences.all
        val fronts = data.keys
        for (front in fronts) {
            val back: String = sharedPreferences.getString(front, "")!!
            flashcards.add(Card(front, back))
        }
    }

    fun showCard(index: Int, front: Boolean) {
        val card = flashcards[index]
        if (front) {
            textView.text = card.front
        }
        else {
            textView.text = card.back
        }
    }

    fun showNextCard() {
        isFront = true
        currentIndex++
        if (currentIndex >= flashcards.size) {
            currentIndex = 0
        }
        showCard(currentIndex, true)
    }

    fun showPreviousCard() {
        isFront = true
        currentIndex--
        if (currentIndex < 0) {
            currentIndex = flashcards.size - 1
        }
        showCard(currentIndex, true)
    }

    fun flipCard() {
        isFront = !isFront
        showCard(currentIndex, isFront)
    }
}