package com.svbneelmane.learn.quotes.viewmodels

/**
 * ViewModel class that is referred by Main Class
 * @author Shivaprasad Bhat
 * @date 23-Jun-2021
 */
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.svbneelmane.learn.quotes.data.Quotes

class MainViewModel(private val context: Context) : ViewModel() {
    private var quoteList: Array<Quotes> = emptyArray()
    private var index = 0

    // Initialize
    init {
        quoteList = loadQuotesFromAssets()
    }

    /**
     * Function that loads the quotes in Json format
     * onto quoteList
     * @return Array<Quotes>
     */
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

    /**
     * Functions that helps to get quote based on index
     *  return Quote
     */

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