package com.ale.tragosapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val idBebida: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = ""

    ):Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList:List<Drink>)