package com.example.testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testing.Repository.loginRepository
import com.example.testing.dataclass.loginresponse
import com.google.gson.JsonObject
import com.taskmo.supermanager.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: loginRepository) : ViewModel() {
    private val _myUiState = MutableStateFlow<Resource<loginresponse>>(Resource.Initial)
    var myUiState: StateFlow<Resource<loginresponse>> = _myUiState.asStateFlow()

    fun login(num: JsonObject) = viewModelScope.launch {
        _myUiState.value = Resource.Loading
        _myUiState.value = loginRepository.getUser(num)
    }
}