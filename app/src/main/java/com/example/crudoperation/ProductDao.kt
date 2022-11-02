package com.example.crudoperation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: Product)

    @Query("SELECT * FROM PRODUCT")
    fun getData():LiveData<List<Product>>

    @Update
    suspend fun update(product: Product)

    @Query("DELETE FROM PRODUCT WHERE id == :productId")
    suspend fun delete(productId: Long)

}