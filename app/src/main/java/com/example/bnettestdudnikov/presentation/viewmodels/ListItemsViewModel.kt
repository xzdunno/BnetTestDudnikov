package com.example.bnettestdudnikov.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bnettestdudnikov.data.model.Drug
import com.example.bnettestdudnikov.domain.Repository
import com.example.dudnikov.data.network.ListenNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemsViewModel @Inject constructor(
    private val repository: Repository,
    private val listenNetwork: ListenNetwork
) : ViewModel() {
    var itemData: MutableLiveData<Drug> = MutableLiveData()
    var listData: MutableLiveData<List<Drug>> = MutableLiveData()
    var searchData: MutableLiveData<List<Drug>> = MutableLiveData()
    val isConnected: Flow<Boolean> = listenNetwork.isConnected

    fun search(query: String) {
        viewModelScope.launch { searchData.value = repository.search(query) }
    }

    fun getData() {
        viewModelScope.launch { listData.value = repository.getData() }
    }

    fun getAllData(): LiveData<List<Drug>> {
        return repository.getAllData()
    }

    fun insertAll(list: List<Drug>) {
        viewModelScope.launch { repository.insertAll(list) }
    }
}