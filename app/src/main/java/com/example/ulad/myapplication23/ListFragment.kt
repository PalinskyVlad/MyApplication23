package com.example.ulad.myapplication23

import android.app.Activity
import android.app.ListFragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.ulad.myapplication23.model.Container
import com.example.ulad.myapplication23.model.Product

class ListFragment : ListFragment() {
    private var listListener: ListListener? = null
    private var products: MutableList<Product> = mutableListOf()

    internal interface ListListener {
        fun itemClicked(id: Long)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                              savedInstanceState: Bundle?): View {
        val cursor = Container.cursor
        println("AVXS")
        if (cursor!!.moveToFirst()) {

            var product = Product(Integer.parseInt(cursor.getString(0)).toLong(),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6)))

            products.add(product)

            while (cursor.moveToNext()) {

                product = Product(Integer.parseInt(cursor.getString(0)).toLong(),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)))
                products.add(product)

            }

        }

        var cursorAdapter: CursorAdapter? = null
        println(cursor.columnNames.toList())
        try {
            println("asd")

            cursorAdapter = SimpleCursorAdapter(inflater.context, R.layout.item,

                    cursor, arrayOf("_id", "name", "UPC", "producer", "price",

                    "shelfLife", "quantity"),

                    intArrayOf(R.id.t1, R.id.t2, R.id.t3, R.id.t4, R.id.t5, R.id.t6, R.id.t7), 0)

        } catch (e: Exception) {
            println("qwe")

            Log.d("tag", e.message)
        }


            listAdapter = cursorAdapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.listListener = activity as ListListener
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        if (listListener != null) {
            listListener!!.itemClicked(id)
        }
    }
}