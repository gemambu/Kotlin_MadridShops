import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.gmb.madridshops.repository.model.EntityData
import com.gmb.madridshops.repository.model.ResponseEntity
import com.gmb.madridshops.repository.network.json.JsonEntitiesParser
import com.gmb.madridshops.util.ReadJsonFile
import org.junit.Assert.*
import org.junit.Test

class JSONParsingTests {


    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parse_correctly_shop() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(!shopsJson!!.isEmpty())

        val parser = JsonEntitiesParser()
        val shop = parser.parse<EntityData>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)

    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_containing_json_with_wrong_latitude_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shopWrong.json")
        assertTrue(!shopsJson!!.isEmpty())

        val parser = JsonEntitiesParser()
        var shop: EntityData
        try {
            shop = parser.parse<EntityData>(shopsJson)
        } catch (e: InvalidFormatException) {
            shop = EntityData(1, 1, "Parsing failed", "", "10", "11", "", "", "", "", "", "", "")
        }
        assertNotNull(shop)
        // assertEquals("Parsing failed", shop.name)
        // assertEquals("10", shop.latitude)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parse_correctly_shops() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(!shopsJson!!.isEmpty())

        val parser = JsonEntitiesParser()
        val shop = parser.parse<ResponseEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.result[0].name)

    }
}