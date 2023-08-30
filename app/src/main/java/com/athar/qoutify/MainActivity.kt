package com.athar.qoutify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainActivityViewModel

    lateinit var quoteText : TextView

    lateinit var author_name : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        quoteText = findViewById(R.id.quote)
        author_name = findViewById(R.id.author_name)

        mainViewModel = ViewModelProvider(this,MainActivityViewModelFactory(application))
            .get(MainActivityViewModel::class.java)

        setQuote(mainViewModel.getQuote())

    }


    fun setQuote(quote:Quote){
        quoteText.text = quote.text
        author_name.text = quote.author
    }
    fun nextQuote(view: View){
        setQuote(mainViewModel.nextQuote())
    }
    fun previousQuote(view: View){

        val currentIndex = mainViewModel.getCurrentIndex()
        if (currentIndex == 0){
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
        }
        else{
            setQuote(mainViewModel.previousQuote())
        }
    }

}