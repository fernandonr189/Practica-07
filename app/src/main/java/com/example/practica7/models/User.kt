package com.example.practica7.models

import java.io.Serializable


class User(var userName: String, var password: String, var orders: ArrayList<Order>) : Serializable {
}