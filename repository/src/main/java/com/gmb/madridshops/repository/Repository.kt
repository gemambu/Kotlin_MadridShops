package com.gmb.madridshops.repository

import com.gmb.madridshops.repository.model.ShopEntity

interface Repository {

    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
}