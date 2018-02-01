package com.gmb.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ShopsResponseEntity (val result: List<ShopEntity>)