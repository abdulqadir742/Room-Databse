package com.example.crudoperation

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val productName: String,
    val productPrice: Int,
    val productQuantity: Int
)
