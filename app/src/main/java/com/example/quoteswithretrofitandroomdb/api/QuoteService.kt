package com.example.quoteswithretrofitandroomdb.api

import com.example.quoteswithretrofitandroomdb.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// STEP 2 - creating interface
interface QuoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>
}
