package com.example.ulad.myapplication23

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.ulad.myapplication23.model.Product

class SecondActivity : AppCompatActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textView = findViewById<View>(R.id.textView) as TextView
        val products = intent.getParcelableArrayListExtra<Product>("products")

        products.filter { it.name == intent.getStringExtra("name") && it.price < intent.getIntExtra("price", Integer.MAX_VALUE)}
                .forEach { textView!!.append(it.toString() + "\n\n") }
    }
}