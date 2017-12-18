package com.example.ulad.myapplication23

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.ulad.myapplication23.model.Product

class ThirdActivity : AppCompatActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        textView = findViewById<View>(R.id.textView) as TextView

        val products = intent.getParcelableArrayListExtra<Product>("products")

        products.filter { it.shelfLife > intent.getIntExtra("shelfLife", Integer.MAX_VALUE) }
                .forEach { textView!!.append(it.toString() + "\n\n") }
    }
}