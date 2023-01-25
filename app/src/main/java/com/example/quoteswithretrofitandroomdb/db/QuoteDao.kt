package com.example.quoteswithretrofitandroomdb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quoteswithretrofitandroomdb.models.Result

// STEP-8 (Step 2 fro room implementation)
@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : List<Result>
}