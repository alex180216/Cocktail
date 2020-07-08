package com.ale.tragosapp.ui.viewmodel

import androidx.lifecycle.*
import com.ale.tragosapp.database.FavoritosCRUD
import com.ale.tragosapp.domain.Repo
import com.ale.tragosapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo:Repo):ViewModel(){

    var crud: FavoritosCRUD? = null

    private val tragoNameData = MutableLiveData<String>()
    private val alcoholicOrnotData = MutableLiveData<String>()
    private val favoritos = MutableLiveData<String>()


    fun setTrago(tragoName:String){
        tragoNameData.value = tragoName
    }

    fun setAlcoholicOrNotFilter(alcoholicOrNot:String){
        alcoholicOrnotData.value = alcoholicOrNot
    }

    fun setFavorito(){
        favoritos.value = crud.toString()
    }




    val fetchTragosList = tragoNameData.distinctUntilChanged().switchMap { tragoName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getTragosList(tragoName))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    val fetchAlcoholicFilter = alcoholicOrnotData.distinctUntilChanged().switchMap { isAlcoholic ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getAlcoholicDrinks(isAlcoholic))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    val obtenerFavoritos = favoritos.distinctUntilChanged().switchMap { fav ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(crud?.let { repo.getFavoritesDrinks(it) })
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }
    init {
        setTrago("chocolate")//para que me agarre al iniciar y no llamar inmediatamente a retrofit
    }




}