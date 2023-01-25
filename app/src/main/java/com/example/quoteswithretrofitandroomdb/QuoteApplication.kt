package com.example.quoteswithretrofitandroomdb

import android.app.Application
import com.example.quoteswithretrofitandroomdb.api.QuoteService
import com.example.quoteswithretrofitandroomdb.api.RetrofitHelper
import com.example.quoteswithretrofitandroomdb.db.QuoteDatabase
import com.example.quoteswithretrofitandroomdb.repository.QuoteRepository

// STEP 11 - by following right approach we have to define it here so other view models can also access it
// STEP 12 - we have to do one entry in manifest
class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}