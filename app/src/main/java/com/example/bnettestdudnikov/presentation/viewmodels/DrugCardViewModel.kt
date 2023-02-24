package com.example.bnettestdudnikov.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bnettestdudnikov.data.model.Drug
import com.example.bnettestdudnikov.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugCardViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    var id: Int = 0
        set(value) {
            field = value
            loadState()
        }

    private fun loadState() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val drug = repository.getRecord(id)
                _state.value =
                    if (drug == null)
                        State.Loaded(repository.getItem(id.toString()), false)
                    else
                        State.Loaded(drug, true)
            } catch (exception: Exception) {
                _state.value = State.Error
            }

        }
    }

    sealed interface State {
        object Loading : State
        data class Loaded(val drug: Drug, val isDb: Boolean) : State
        object Error : State
    }
}