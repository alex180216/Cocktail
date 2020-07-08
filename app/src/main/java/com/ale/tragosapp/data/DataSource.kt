package com.ale.tragosapp.data

import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.database.FavoritosCRUD
import com.ale.tragosapp.utils.Resource
import com.ale.tragosapp.utils.RetrofitClient

class DataSource{

    suspend fun getTragoByName(tragoName:String):Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragoByName(tragoName).drinkList)
    }

    suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getAlcoholicDrink(alcoholic!!).drinkList)
    }

    suspend fun getFavDrinks(crud: FavoritosCRUD): Resource<List<Drink>>{
        return Resource.Success(crud.getDrinks())
    }

}