package com.svbneelmane.learn.quotes.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.svbneelmane.learn.quotes.data.Quotes
import com.svbneelmane.learn.quotes.databinding.ActivityMainBinding
import com.svbneelmane.learn.quotes.viewmodels.MainViewModel
import com.svbneelmane.learn.quotes.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)

        setQuote(viewModel.getQuote())

        with(binding) {
            tvPrevious.setOnClickListener {
                onPrevious()
            }
            tvNext.setOnClickListener {
                onNext()
            }
            floatingActionButton.setOnClickListener {
                onShare()
            }
        }

    }

    private fun setQuote(quotes: Quotes) {
        with(binding) {
            quoteText.text = quotes.text
            quoteAuthor.text = quotes.author
        }
    }

    private fun onPrevious() {
        setQuote(viewModel.prevQuote())
    }

    private fun onNext() {
        setQuote(viewModel.nextQuote())
    }

    private fun onShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, viewModel.getQuote().text)
        startActivity(intent)
    }
}
