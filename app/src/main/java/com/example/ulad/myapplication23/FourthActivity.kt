package com.example.ulad.myapplication23

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.database.Cursor
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.view.ContextMenu
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.ulad.myapplication23.model.Product
import java.nio.file.Files.size
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase
import com.example.ulad.myapplication23.model.Container
import com.example.ulad.myapplication23.model.DB

class FourthActivity : AppCompatActivity(), ListFragment.ListListener,DetailFragment.DataPass {

    private val container: Container? = null

    private var cursor: Cursor? = null

    private var detailFragment: DetailFragment? = null

    private var listViewFragment: ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        println("WORRK")

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fourth)

        cursor = Container.cursor

        if (cursor == null) {
            println("WORRK")
println("WORRK")
println("WORRK")
println("WORRK")
println("WORRK")
            detailFragment = DetailFragment()

            fragmentManager.beginTransaction().add(R.id.fragment, detailFragment).commit()

        } else {

            listViewFragment = ListFragment()

            fragmentManager.beginTransaction().add(R.id.fragment, listViewFragment).commit()

        }

    }

    override fun itemClicked(id: Long) {
        println("WORRK")
        println("WORRK")
        println("WORRK")

        val bundle = Bundle()

        val detailFragment = DetailFragment()

        val database = DB(this)

        val sqLiteDatabase = database.readableDatabase

        cursor = sqLiteDatabase.query("PRODUCT2", null, "_id = ?",

                arrayOf(id.toString()), null, null, null)

        var product: Product? = null

        if (cursor!!.moveToFirst()) {

            product = Product(java.lang.Long.parseLong(cursor!!.getString(0)),
                    cursor!!.getString(1),
                    cursor!!.getString(2),
                    cursor!!.getString(3),
                    Integer.parseInt(cursor!!.getString(4)),
                    Integer.parseInt(cursor!!.getString(5)),
                    Integer.parseInt(cursor!!.getString(6)))

        }

        cursor!!.close()

        bundle.putParcelable("product", product)

        detailFragment.arguments = bundle

        fragmentManager.beginTransaction().replace(R.id.fragment, detailFragment).addToBackStack("detail").commit()
    }

    override fun onDataPass() {

        if (listViewFragment != null) {

            listViewFragment = ListFragment()

            fragmentManager.popBackStack()

            fragmentManager.beginTransaction().replace(R.id.fragment, listViewFragment).commit()

            cursor = Container.cursor

            cursor!!.requery()

        } else {

            finish()

            Toast.makeText(applicationContext, "Add", Toast.LENGTH_LONG).show()

        }

    }
}
