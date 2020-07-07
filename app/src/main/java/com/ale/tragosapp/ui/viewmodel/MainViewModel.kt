package com.ale.tragosapp.ui.viewmodel

import androidx.lifecycle.*
import com.ale.tragosapp.data.model.Drink
import com.ale.tragosapp.domain.Repo
import com.ale.tragosapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo:Repo):ViewModel(){

    private val tragoNameData = MutableLiveData<String>()
    private val alcoholicOrnotData = MutableLiveData<String>()
    private val favoritsData = MutableLiveData<String>()

    fun setTrago(tragoName:String){
        tragoNameData.value = tragoName
    }

    fun setAlcoholicOrNotFilter(alcoholicOrNot:String){
        alcoholicOrnotData.value = alcoholicOrNot
    }
    fun setFavorito(favorito: Drink){
        favoritsData.value = favorito.toString()
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
    init {
        setTrago("chocolate")//para que me agarre al iniciar y no llamar inmediatamente a retrofit
    }

    val getFavList = favoritsData.distinctUntilChanged().switchMap { isFavorit ->
        liveData(Dispatchers.IO) {

        }

    }


}