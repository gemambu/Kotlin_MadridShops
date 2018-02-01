package com.gmb.madridshops.repository

interface Repository {
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}