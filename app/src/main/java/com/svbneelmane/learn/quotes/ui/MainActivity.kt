package com.svbneelmane.learn.quotes.ui

/**
 * Entry Point to the Application
 * @author Shivaprasad Bhat
 * @date 23-Jun-2021
 */
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.svbneelmane.learn.quotes.data.Quotes
import com.svbneelmane.learn.quotes.databinding.ActivityMainBinding
import com.svbneelmane.learn.quotes.viewmodels.MainViewModel
import com.svbneelmane.learn.quotes.viewmodels.MainViewModelFactory
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create ViewModel using the factory class
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)

        setQuote(viewModel.getQuote())

        /**
         * Set On Click Listeners to Prev, Next and Share buttons
         */
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

    /**
     * Function that sets the quotes on the UI
     * Uses ViewBinding
     */
    private fun setQuote(quotes: Quotes) {
        with(binding) {
            quoteText.text = quotes.text
            quoteAuthor.text = quotes.author
        }
    }

    /**
     * Function that acts on click of Previous button
     */
    private fun onPrevious() {
        setQuote(viewModel.prevQuote())
    }

    /**
     * Function that acts on click of Next button
     */
    private fun onNext() {
        setQuote(viewModel.nextQuote())
    }


    /**
     * Function that acts on click of Share floating button
     */
    private fun onShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, viewModel.getQuote().text)
        startActivity(intent)
    }
}
