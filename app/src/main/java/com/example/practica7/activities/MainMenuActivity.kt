package com.example.practica7.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.practica7.R
import com.example.practica7.models.Order
import com.example.practica7.models.Product
import com.example.practica7.models.User
import com.google.gson.Gson


class MainMenuActivity : AppCompatActivity() {
    private lateinit var orderButton: Button
    private lateinit var productsButton: Button
    private lateinit var ordersButton: Button
    private lateinit var aboutButton: Button
    private lateinit var logoutButton: Button
    private lateinit var welcomeLabel: TextView
    private lateinit var user: User

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                val result = data?.getStringExtra("Order")
                val gson = Gson()
                val order = gson.fromJson(result, Order::class.java)
                user.orders.add(order)
                saveUser(user.userName, user.password, user.orders)
            }
        }
        user.orders.map {
            println(it.product.productName)
        }
    }

    private fun saveUser(username: String, password: String, orders: ArrayList<Order>) {
        user = User(username, password, orders)
        val editor = getSharedPreferences("User", MODE_PRIVATE).edit()
        val gson = Gson()
        val jsonData = gson.toJson(user)
        editor.putString("User", jsonData)
        editor.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val id = intent.getStringExtra("User")
        val gson = Gson()
        user = gson.fromJson(id, User::class.java)

        orderButton = findViewById(R.id.order_button)
        productsButton = findViewById(R.id.products_button)
        ordersButton = findViewById(R.id.orders_button)
        aboutButton = findViewById(R.id.about_button)
        logoutButton = findViewById(R.id.logout_button)
        welcomeLabel = findViewById(R.id.welcome_text)

        welcomeLabel.setText("Bienvenido " + user.userName + "!")

        orderButton.setOnClickListener {
            goToPlaceOrder()
        }

        logoutButton.setOnClickListener {
            finish()
        }

        productsButton.setOnClickListener {
            goToProducts()
        }

        ordersButton.setOnClickListener {
            goToOrders()
        }
    }

    private fun goToPlaceOrder() {
        val intent = Intent(this, OrdersFormActivity::class.java).apply {

        }
        startActivityForResult(intent, 1)
    }

    private fun goToProducts() {
        val intent = Intent(this, ProductsActivity::class.java).apply {

        }
        startActivity(intent)
    }

    private fun goToOrders() {
        val intent = Intent(this, OrdersListActivity::class.java).apply {
            val gson = Gson()
            val jsonData = gson.toJson(user.orders)
            intent.putExtra("Orders", jsonData)
        }
        startActivity(intent)
    }

}