package com.smartherd.multiplescreensupport

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.fragments.R
import kotlinx.android.synthetic.main.activity_main.*
import android.R
import android.util.Log
import android.R
import android.content.Context
import android.support.v4.app.FragmentManager
import android.R




class MainActivity : AppCompatActivity(), MyCommunicator {

    private var mIsDualPane = false
    var fragmentManager: FragmentManager? = null
    var logOutListener: LogOutListener? = null
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fragments.R.layout.activity_main)


        fragmentA.view?.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        val fragmentBView = findViewById<View>(com.example.fragments.R.id.fragmentB)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(title: String, description: String) {

        if (mIsDualPane) {
            val fragmentB = supportFragmentManager.findFragmentById(com.example.fragments.R.id.fragmentB) as FragmentB?
            fragmentB?.displayDetails(title, description)


        } else {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            startActivity(intent)
        }
    }
    fun logOut(v: View) {
        Log.e("my", "Log out")
        logOutListener = MainActivity.context as LogOutListener
        logOutListener.out()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    interface LogOutListener {
        fun out()
    }
}