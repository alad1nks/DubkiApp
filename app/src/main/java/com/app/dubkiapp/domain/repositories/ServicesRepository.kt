package com.app.dubkiapp.domain.repositories

import com.app.dubkiapp.ui.model.Service

interface ServicesRepository {

    fun getServices(): List<Service>

}