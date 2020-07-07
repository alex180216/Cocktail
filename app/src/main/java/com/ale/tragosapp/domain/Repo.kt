package com.ale.tragosapp.domain

import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.utils.Resource

interface Repo {
    suspend fun getTragosList(tragoName:String): Resource<List<Drink>>
    suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>>
    suspend fun getFavDrinks()
}