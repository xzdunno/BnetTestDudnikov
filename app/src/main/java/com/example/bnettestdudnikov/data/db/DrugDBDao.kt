package com.example.bnettestdudnikov.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bnettestdudnikov.data.model.Drug

@Dao
interface DrugDBDao {
    @Query("select * from DBDrug order by id asc")
    fun getAllData(): LiveData<List<Drug>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(drug: Drug)

    @Query("select * from DBDrug where id=:id")
    fun getRecord(id: Int): Drug?

    @Query("delete from DBDrug")
    fun deleteAll()

    @Query("delete from DBDrug where id=:id")
    fun deleteRecord(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(list: List<Drug>)
}