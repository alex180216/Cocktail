package com.ale.tragosapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class FavoritasDbHelper(context: Context) : SQLiteOpenHelper(context, Favoritos.Bebidas.FAVORITOS_NAME, null,
    DATABASE_VERSION
) {

    private val db: SQLiteDatabase
    private val values: ContentValues


    init {
        db = this.writableDatabase
        values = ContentValues()
    }

    companion object{
        val DATABASE_VERSION = 1

        val CREATE_TABLE_DRINKS = "CREATE TABLE " + Favoritos.Bebidas.FAVORITOS_NAME +
                                " (" + Favoritos.Bebidas.COLUMN_ID + " TEXT PRIMARY KEY, " +
                                        Favoritos.Bebidas.COLUMN_NAME + " TEXT, " +
                                        Favoritos.Bebidas.COLUMN_IMG + " TEXT, " +
                                        Favoritos.Bebidas.COLUMN_INSTRUCCIONS + " TEXT )"

        val REMOVE_DRINKS = "DROP TABLE IF EXISTS" + Favoritos.Bebidas.FAVORITOS_NAME


    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_DRINKS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_DRINKS)
        onCreate(db)
    }



}