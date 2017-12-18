package com.example.ulad.myapplication23

import android.os.Bundle
import android.widget.TextView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.ulad.myapplication23.model.Product

class FirstActivity : AppCompatActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        textView = findViewById<View>(R.id.textView) as TextView
        val products = intent.getParcelableArrayListExtra<Product>("products")

        products.filter { it.name == intent.getStringExtra("name") }.forEach { textView!!.append(it.toString() + "\n\n") }
    }
}