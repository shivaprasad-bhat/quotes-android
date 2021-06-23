package com.svbneelmane.learn.quotes.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.svbneelmane.learn.quotes.data.Quotes

class MainViewModel(private val context: Context) : ViewModel() {
    private var quoteList: Array<Quotes> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)

        val gson = Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote(): Quotes {
        return if (index < quoteList.size - 1) {
            quoteList[++index]
        } else {
            quoteList[index]
        }
    }

    fun prevQuote(): Quotes {
        return if (index > 0) {
            quoteList[--index]
        } else {
            quoteList[index]
        }
    }
}