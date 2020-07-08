package com.ale.tragosapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.ale.tragosapp.data.model.Drink

class FavoritosCRUD (context: Context){
    private var helper: FavoritasDbHelper? = null

    init{
        helper = FavoritasDbHelper(context)
    }

    fun newDrink(item:Drink){
        val db: SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()

        //mapeo de complumnas con valores
        values.put(Favoritos.Bebidas.COLUMN_ID, item.idBebida)
        values.put(Favoritos.Bebidas.COLUMN_NAME, item.nombre)
        values.put(Favoritos.Bebidas.COLUMN_IMG, item.imagen)
        values.put(Favoritos.Bebidas.COLUMN_INSTRUCCIONS, item.descripcion)


        //insertar en la tabla
        val newRowId = db.insert(Favoritos.Bebidas.FAVORITOS_NAME, null, values)

        db.close()
    }

    fun getDrinks(): ArrayList<Drink>{

        val items: ArrayList<Drink> = ArrayList()

        val db: SQLiteDatabase = helper?.readableDatabase!!

        //columnas que quiero consultar
        val columnas = arrayOf(
            Favoritos.Bebidas.COLUMN_ID, Favoritos.Bebidas.COLUMN_NAME,
                                    Favoritos.Bebidas.COLUMN_IMG, Favoritos.Bebidas.COLUMN_INSTRUCCIONS)

        val c: Cursor = db.query(
            Favoritos.Bebidas.FAVORITOS_NAME,
                                columnas,
                                null, null, null, null, null)

        while (c.moveToNext()){
            items.add(Drink(c.getString(c.getColumnIndexOrThrow(Favoritos.Bebidas.COLUMN_ID)),
                            c.getString(c.getColumnIndexOrThrow(Favoritos.Bebidas.COLUMN_NAME)),
                            c.getString(c.getColumnIndexOrThrow(Favoritos.Bebidas.COLUMN_IMG)),
                            c.getString(c.getColumnIndexOrThrow(Favoritos.Bebidas.COLUMN_INSTRUCCIONS))
            ))
        }

        db.close()
        return items
    }

    fun deleteDrink(item: Drink){
        val db:SQLiteDatabase = helper?.writableDatabase!!

        db.delete(Favoritos.Bebidas.FAVORITOS_NAME, "_id = ?", arrayOf(item.idBebida))

        db.close()
    }
}