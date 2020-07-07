package com.ale.tragosapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Drink(
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = "",
    @SerializedName("idDrink")
    val idBebida: String = "",
    @SerializedName("strIngredient1")
    val ingrediente1: String = "",
    @SerializedName("strIngredient2")
    val ingrediente2: String = "",
    @SerializedName("strIngredient3")
    val ingrediente3: String = "",
    @SerializedName("strIngredient4")
    val ingrediente4: String = ""
):Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList:List<Drink>)