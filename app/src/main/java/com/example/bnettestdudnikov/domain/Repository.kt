package com.example.bnettestdudnikov.domain

import androidx.lifecycle.LiveData
import com.example.bnettestdudnikov.data.db.DrugDBDao
import com.example.bnettestdudnikov.data.model.Drug
import com.example.bnettestdudnikov.data.network.RetroInterface
import javax.inject.Inject

class Repository @Inject constructor(
    private val retroInterface: RetroInterface,
    private val drugDBDao: DrugDBDao
) {
    suspend fun getItem(id: String): Drug {
        val resp = retroInterface.getItem(id)
        return resp.body()!!
    }

    suspend fun getData(): List<Drug> {
        val list = mutableListOf<Drug>()
        for (i in 1..10) {
            val resp = retroInterface.getData(i.toString())
            list.addAll(resp.body()!!)
        }
        return list
    }

    fun getAllData(): LiveData<List<Drug>> {
        return drugDBDao.getAllData()
    }

    suspend fun insertAll(list: List<Drug>) {
        drugDBDao.insertAll(list)
    }

    fun getRecord(id: Int): Drug? = drugDBDao.getRecord(id)

    suspend fun search(query: String): List<Drug> {
        val resp = retroInterface.getSearch(query)
        return resp.body()!!
    }
}