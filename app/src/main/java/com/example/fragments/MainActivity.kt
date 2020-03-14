package com.smartherd.multiplescreensupport

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.fragments.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyCommunicator {

    private var mIsDualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentA.view?.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        val fragmentBView = findViewById<View>(R.id.fragmentB)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(title: String, description: String) {

        if (mIsDualPane) {
            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentB?
            fragmentB?.displayDetails(title, description)
        } else { 
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            startActivity(intent)
        }
    }
}