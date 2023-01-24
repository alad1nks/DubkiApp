package com.app.dubkiapp.ui.services

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.dubkiapp.domain.Service
import com.app.dubkiapp.repositories.ServicesRepository
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