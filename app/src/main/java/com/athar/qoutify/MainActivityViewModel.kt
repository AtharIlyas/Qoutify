package com.athar.qoutify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainActivityViewModel(val context: Context) : ViewModel() {
     var quotesArray : Array<Quote> = emptyArray()
     var index = 0

    init {
        quotesArray = getQuoteFromAsset()
    }

    private fun getQuoteFromAsset(): Array<Quote> {

        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getCurrentIndex() : Int{
        return index
    }
    fun getQuote() = quotesArray[index]
    fun nextQuote() = quotesArray[++index]
    fun previousQuote() = quotesArray[--index]

}