package com.ale.tragosapp.database

import com.ale.tragosapp.data.model.Drink

 class Favoritos {
    abstract class Bebidas{
        companion object {
            val FAVORITOS_NAME = "drink"
            val COLUMN_ID = "_id"
            val COLUMN_NAME = "nombre"
            val COLUMN_IMG = "imagen"
            val COLUMN_INSTRUCCIONS ="instrucciones"
            var bebidas: MutableList<Drink> = ArrayList()
        }
    }


}