package com.example.practica7.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.practica7.R
import com.example.practica7.models.Order
import com.example.practica7.models.Product
import com.example.practica7.models.User
import com.example.practica7.models.size
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson


class OrdersFormActivity : AppCompatActivity() {
    private lateinit var addressTextField: TextInputEditText
    private lateinit var productSpinner: Spinner
    private lateinit var sizeSpinner : Spinner
    private lateinit var phoneTextField: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var cancelButton: Button

    private val products = listOf<Product> (
        Product("Disfraz mclovin", 2000.0),
        Product("Disfraz Zoro", 2400.0),
        Product("Disfraz Nami", 3453.0),
        Product("Disfraz Luffy", 3234.0),
        Product("Disfraz Maik wasauski", 6456.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orders_form)

        addressTextField = findViewById(R.id.address_textfield)
        productSpinner = findViewById(R.id.products_spinner)
        sizeSpinner = findViewById(R.id.size_spinner)
        phoneTextField = findViewById(R.id.phone_textfield)
        registerButton = findViewById(R.id.register_order_button)
        cancelButton = findViewById(R.id.cancel_order_button)

        val products_list = ArrayList<String>()

        for (product in products) {
            products_list.add(product.productName)
            println(product.productName)
        }

        val adapter = ArrayAdapter(this, R.layout.spinner_element, products_list)
        adapter.setDropDownViewResource(R.layout.spinner_element)
        productSpinner.adapter = adapter

        cancelButton.setOnClickListener {
            finish()
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.Sizes,
            R.layout.spinner_element
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_element)
            sizeSpinner.adapter = adapter
        }

        cancelButton.setOnClickListener {
            finish()
        }

        registerButton.setOnClickListener {
            if(formIsValid()) {
                val product = getProduct()
                val newOrder = Order(addressTextField.text.toString(), product, phoneTextField.text.toString(), getSize())
                val resultIntent = Intent()
                val gson = Gson()
                val jsonData = gson.toJson(newOrder)
                resultIntent.putExtra("Order", jsonData)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun getSize(): size {
        when(sizeSpinner.selectedItem.toString()) {
            "Chico" -> return size.SMALL
            "Mediano" -> return size.MEDIUM
            "Grande" -> return size.LARGE
        }
        return size.SMALL
    }

    private fun getProduct() : Product {
        products.map {
            if(it.productName == productSpinner.selectedItem.toString()) {
                return it;
            }
        }
        return Product("None", 20.0)
    }

    private fun formIsValid() : Boolean{
        if(addressTextField.text.isNullOrBlank() ||
            phoneTextField.text.isNullOrBlank()
            ) {
            return false
        }
        return true
    }

}