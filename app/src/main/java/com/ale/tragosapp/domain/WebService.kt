package com.ale.tragosapp.domain

import com.ale.tragosapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getTragoByName(@Query("s") tragoName:String): DrinkList

    @GET("filter.php")
    suspend fun getAlcoholicDrink(@Query("a") alcoholicOrNot:String): DrinkList
}