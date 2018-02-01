package com.rodrigolc.madridshops


import com.gmb.madridshops.repository.model.ShopEntity
import com.gmb.madridshops.repository.model.ShopsResponseEntity
import com.gmb.madridshops.repository.network.json.JsonEntitiesParser
import com.gmb.madridshops.util.ReadJsonFile
import org.junit.Assert.*
import org.junit.Test

class JSONParsingTests {


    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parse_correctly_shop() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(false == shopsJson!!.isEmpty())

        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)

    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parse_correctly_shops() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(false == shopsJson!!.isEmpty())

        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopsResponseEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.result[0].name)

    }
}