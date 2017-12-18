package com.example.ulad.myapplication23

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.design.widget.NavigationView
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.example.ulad.myapplication23.model.Product
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import android.widget.ArrayAdapter
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.support.annotation.NonNull
import android.view.MenuInflater
import android.database.sqlite.SQLiteDatabase
import com.example.ulad.myapplication23.model.Container
import com.example.ulad.myapplication23.model.DB


class MainActivity : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText

    private var drawerLayout: DrawerLayout? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null

    private val TAG = "myTag"

    private val objectMapper = ObjectMapper()
    private var products = arrayListOf<Product>()

    private var database: DB? = null

    private var sqLiteDatabase: SQLiteDatabase? = null

    private val container = Container()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater

        inflater.inflate(R.menu.menu, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {

            return true

        } else {

            when (item.itemId) {

                R.id.item1 -> {

                    Container.cursor = (null)

                    val intent = Intent(this, FourthActivity::class.java)

                    startActivity(intent)

                    return true

                }
            }

        }

        return super.onOptionsItemSelected(item)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById<View>(R.id.editText1) as EditText
        editText2 = findViewById<View>(R.id.editText2) as EditText

        database = DB(this)

        sqLiteDatabase = database!!.readableDatabase
        button1 = findViewById<View>(R.id.button1) as Button
        button2 = findViewById<View>(R.id.button2) as Button
        button3 = findViewById<View>(R.id.button3) as Button
        button4 = findViewById<View>(R.id.button4) as Button

        button1.setOnClickListener {
            Container.cursor = (sqLiteDatabase!!.query("PRODUCT2", null, "name = ?",  arrayOf(editText1.text.toString()), null, null, null))
            val intent = Intent(this@MainActivity, FourthActivity::class.java)

            startActivity(intent)
        }

        button2 = findViewById<View>(R.id.button2) as Button

        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, FourthActivity::class.java)

            Container.cursor = (sqLiteDatabase!!.query("PRODUCT2", null, "producer = ?",

                    arrayOf(editText2.text.toString()), null, null, null))
            startActivity(intent)

        }

        button3 = findViewById<View>(R.id.button3) as Button

        button3.setOnClickListener {
            Container.cursor = (sqLiteDatabase!!.query("PRODUCT2", null, "quantity > 0", null, null, null, null))
            val intent = Intent(this@MainActivity, FourthActivity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            Container.cursor = sqLiteDatabase!!.query("PRODUCT2", null, null, null, null, null, null)


            val intent = Intent(this@MainActivity, FourthActivity::class.java)

            startActivity(intent)
        }

        drawerLayout = findViewById<View>(R.id.drawer) as DrawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawerLayout!!.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView!!.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.map -> {
                    val intent = Intent(this@MainActivity, FifthActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.list -> {
                    val intent = Intent(this@MainActivity, FourthActivity::class.java)
                    intent.putParcelableArrayListExtra("products", products)
                    startActivityForResult(intent, 3)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

}
