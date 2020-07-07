package com.ale.tragosapp.data

import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.vo.Resource
import com.ale.tragosapp.vo.RetrofitClient

class DataSource{

    suspend fun getTragoByName(tragoName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragoByName(tragoName).drinkList)
    }

    suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getAlcoholicDrink(alcoholic!!).drinkList)
    }
}