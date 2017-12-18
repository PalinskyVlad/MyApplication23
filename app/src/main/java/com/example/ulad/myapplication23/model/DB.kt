package com.example.ulad.myapplication23.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(context: Context) : SQLiteOpenHelper(context, "mydb", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE PRODUCT2(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name TEXT," +
                "UPC TEXT," +
                "producer TEXT," +
                "price INTEGER," +
                "shelfLife INTEGER," +
                "quantity INTEGER);")
        db.insert("PRODUCT2", null, insertProduct("name1", "Cambodia", "producer1", 7103, 2000, 0))
        db.insert("PRODUCT2", null, insertProduct("name2", "Dominica", "producer2", 5315, 1000, 4000))
        db.insert("PRODUCT2", null, insertProduct("name3", "Venezuela", "producer3", 846, 2000, 4000))
        db.insert("PRODUCT2", null, insertProduct("name4", "Bahrain", "producer4", 2439, 500, 0))
        db.insert("PRODUCT2", null, insertProduct("name5", "Austria", "producer5", 18706, 6000, 400))
        db.insert("PRODUCT2", null, insertProduct("name6", "Spain", "producer4", 9362, 2000, 40000))
        db.insert("PRODUCT2", null, insertProduct("name7", "Liechtenstein", "producer3", 3162, 2000, 4000))
        db.insert("PRODUCT2", null, insertProduct("name8", "Norway", "producer2", 6359, 20000, 4000))
        db.insert("PRODUCT2", null, insertProduct("name9", "Somalia", "producer1", 4775, 9000, 6500))
        db.insert("PRODUCT2", null, insertProduct("name2", "Switzerland", "producer2", 8087, 7000, 400))
        db.insert("PRODUCT2", null, insertProduct("name1", "Switzerland", "producer3", 8097, 7060, 400))


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun insertProduct(name: String, UPC: String, producer: String, price: Int, shelfLife: Int, quantity: Int) = ContentValues().apply {
            put("name", name)
            put("UPC", UPC)
            put("producer", producer)
            put("price", price)
            put("shelfLife", shelfLife)
            put("quantity", quantity)
        }
}