package com.example.practica7.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica7.R
import com.example.practica7.models.Order
import com.example.practica7.models.Product

class RecyclerAdapterOrders(private val mList: List<Order>) : RecyclerView.Adapter<RecyclerAdapterOrders.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_element_orders, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.nameTextView.text = ItemsViewModel.product.productName
        holder.priceTextView.text = ItemsViewModel.product.price.toString()
        holder.addressTextView.text = ItemsViewModel.address
        holder.sizeTextView.text = ItemsViewModel.size.toString()

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val priceTextView: TextView = itemView.findViewById(R.id.price)
        val addressTextView: TextView = itemView.findViewById(R.id.address)
        val sizeTextView: TextView = itemView.findViewById(R.id.size)
    }
}