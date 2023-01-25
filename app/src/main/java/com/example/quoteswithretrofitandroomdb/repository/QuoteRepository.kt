package com.example.quoteswithretrofitandroomdb.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quoteswithretrofitandroomdb.api.QuoteService
import com.example.quoteswithretrofitandroomdb.db.QuoteDatabase
import com.example.quoteswithretrofitandroomdb.models.QuoteList
import com.example.quoteswithretrofitandroomdb.utils.NetworkUtils

// STEP 4 - QuoteService
// STEP 10 - QuoteDatabase
class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {
    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int){

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quoteService.getQuotes(page)
            if(result?.body() != null){
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())

                Log.d("check", "Retrofit")
            }
        }
        else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes, 1, 1)
            quotesLiveData.postValue(quoteList)

            Log.d("check", "Room DB")
        }
    }
}
