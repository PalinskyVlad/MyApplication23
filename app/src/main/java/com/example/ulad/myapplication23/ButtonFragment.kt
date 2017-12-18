package com.example.ulad.myapplication23

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

class ButtonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) = inflater!!.inflate(R.layout.button_fragment, container, false)!!

}