package com.example.robustatask.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.robustatask.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView = findViewById<SearchView>(R.id.search_product)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                val productsFragment =
                    ProductsListFragment.newInstance(0)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.products, productsFragment, "Products")
                    .addToBackStack(null)
                    .commit()
                return true
            }
        })
    }
}