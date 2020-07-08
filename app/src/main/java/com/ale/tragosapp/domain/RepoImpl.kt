package com.ale.tragosapp.domain

import com.ale.tragosapp.data.DataSource
import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.database.FavoritosCRUD
import com.ale.tragosapp.utils.Resource

class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getTragosList(tragoName:String): Resource<List<Drink>> {
        return dataSource.getTragoByName(tragoName)
    }

    override suspend fun getAlcoholicDrinks(alcoholic: String?): Resource<List<Drink>> {
        return dataSource.getAlcoholicDrinks(alcoholic)
    }

    override suspend fun getFavoritesDrinks(crud: FavoritosCRUD): Resource<List<Drink>> {
        return dataSource.getFavDrinks(crud)
    }


}