package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent as Intent1

class MainActivity : AppCompatActivity() {

    val arrayList = ArrayList<Model>()
    val displayList = ArrayList<Model>()

    lateinit var homeFragment: HomeFragment
    lateinit var aboutUsFragment: AboutUsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttoncamera = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        buttoncamera.setOnClickListener{
            val intent = Intent1(this,PhotoActivity::class.java)
            startActivity(intent)
        }
        //here bardrawer

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        val bottomNavigation:BottomNavigationView = findViewById(R.id.btm_view)

        bottomNavigation.setOnNavigationItemReselectedListener { item ->

            when(item.itemId) {
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




        arrayList.add(Model("Geanta","100 RON", R.drawable.geanta ))
        arrayList.add(Model("Rochie","200 RON", R.drawable.rochie ))
        arrayList.add(Model("Geaca de blugi","150 RON", R.drawable.geaca ))
        arrayList.add(Model("Rochie de seara","400 RON", R.drawable.rochie_seara ))
        arrayList.add(Model("Fusta","170 RON RON", R.drawable.fusta ))

        displayList.addAll(arrayList)

        val myAdapter = MyAdapter(displayList,this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val menuItem = menu!!.findItem(R.id.search)

        if(menuItem != null){
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        arrayList.forEach{
                            if(it.title.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    else {
                        displayList.clear()
                        displayList.addAll(arrayList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return  true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }

}