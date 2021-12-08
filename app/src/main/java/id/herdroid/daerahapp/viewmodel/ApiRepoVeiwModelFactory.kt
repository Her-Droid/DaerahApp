package id.herdroid.daerahapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.herdroid.daerahapp.model.repository.ApiRepo

class ApiRepoVeiwModelFactory(): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            return ApiViewModel(ApiRepo()) as T
        }
        else throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}