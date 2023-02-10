package com.app.dubkiapp.ui.stateholders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.dubkiapp.ui.model.Service
import com.app.dubkiapp.domain.repositories.ServicesRepository
import javax.inject.Inject

class ServicesViewModel @Inject constructor(
    private val servicesRepository: ServicesRepository
): ViewModel() {
    val services: MutableLiveData<List<Service>> by lazy {
        MutableLiveData<List<Service>>()
    }
    init {
        getServices()
    }

    private fun getServices() {
        services.postValue(servicesRepository.getServices())
    }
}