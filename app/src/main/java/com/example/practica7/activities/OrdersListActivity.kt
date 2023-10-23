package com.example.practica7.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica7.R
import com.example.practica7.adapters.RecyclerAdapterOrders
import com.example.practica7.adapters.RecyclerAdapterProducts
import com.example.practica7.models.User
import com.google.gson.Gson

class OrdersListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orders_list)
        getUser()

        val orders = user.orders

        recyclerView = findViewById(R.id.orders_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerAdapterOrders(orders)

        recyclerView.adapter = adapter
    }

    private fun getUser() {
        val pref = getSharedPreferences("User", MODE_PRIVATE)
        val jsonData = pref.getString("User", "")
        if(!jsonData.isNullOrEmpty()) {
            val gson = Gson()
            user = gson.fromJson(jsonData, User::class.java)
        }
    }
}