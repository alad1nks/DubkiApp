package com.app.dubkiapp.repositories

import com.app.dubkiapp.domain.Service

interface ServicesRepository {

    fun getServices(): List<Service>

}