package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class NavigationButton : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var homeFragment: HomeFragment
        lateinit var aboutUsFragment: AboutUsFragment

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //here bardrawer

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        val bottomNavigation: BottomNavigationView = findViewById(R.id.btm_view)

        bottomNavigation.setOnNavigationItemReselectedListener { item ->

            when (item.itemId) {
                R.id.home -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.about_us -> {
                    aboutUsFragment = AboutUsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, aboutUsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            true
        }
    }
}
