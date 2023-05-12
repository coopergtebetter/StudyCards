package com.example.studycards

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studycards.databinding.ActivityCreateNewCardSetBinding

class CreateNewCardSet : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNewCardSetBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewCardSetBinding.inflate(layoutInflater) 
        
        setContentView(binding.root)
        
        val title = binding.createTitle
        val description = binding.createDesc
        val submitButton = binding.button3
        
        submitButton.setOnClickListener { 
            val t = title.text.toString()
            val d = description.text.toString()
            if (t == "") {
                Toast.makeText(this, "please enter a title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (d == "") {
                Toast.makeText(this, "please enter a description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val prefEdit = this.getSharedPreferences("sets", Context.MODE_PRIVATE).edit()
            prefEdit.putString(t,d)
            prefEdit.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}