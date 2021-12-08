package id.herdroid.daerahapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.herdroid.daerahapp.model.DaerahModel
import id.herdroid.daerahapp.model.UserModel
import id.herdroid.daerahapp.model.repository.ApiRepo

class ApiViewModel(private val apiRepo: ApiRepo) : ViewModel() {
    var userModel = UserModel()
    var daerahModel = DaerahModel()

    fun getLogin(): LiveData<UserModel> {
        return apiRepo.getLogin(userModel)
    }

    fun getProvinsi(): LiveData<List<DaerahModel>> {
        return apiRepo.getProvinsi()
    }

    fun getKota(): LiveData<List<DaerahModel>> {
        return apiRepo.getKota(daerahModel)
    }

    fun getKecamatan(): LiveData<List<DaerahModel>> {
        return apiRepo.getKecamatan(daerahModel)
    }

    fun getKelurahan(): LiveData<List<DaerahModel>> {
        return apiRepo.getKelurahan(daerahModel)
    }

    fun getKodePos(): LiveData<List<DaerahModel>> {
        return apiRepo.getKodePos(daerahModel)
    }
}