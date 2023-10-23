package com.example.practica7.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica7.R
import com.example.practica7.adapters.RecyclerAdapterProducts
import com.example.practica7.models.Product

class ProductsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    private val products = listOf<Product> (
        Product("Disfraz mclovin", 2000.0),
        Product("Disfraz Zoro", 2400.0),
        Product("Disfraz Nami", 3453.0),
        Product("Disfraz Luffy", 3234.0),
        Product("Disfraz Maik wasauski", 6456.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.products)

        recyclerView = findViewById(R.id.products_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerAdapterProducts(products)

        recyclerView.adapter = adapter
    }
}