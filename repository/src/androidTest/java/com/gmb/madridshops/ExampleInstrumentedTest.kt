package com.gmb.madridshops

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.gmb.madridshops.repository.db.buildHelper
import com.gmb.madridshops.repository.db.dao.EntityDAO
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    // Context of the app under test.
    private val appContext = InstrumentationRegistry.getTargetContext()
    private val dbHelper = buildHelper(appContext, "MadridShops.sqlite", 1)

    @Test
    fun given_valid_shopentity_it_gets_inserted_correctly() {


        //val shop = ShopEntity(33, 1, "my shop", "",
         //       1.0f, 2.0f, "", "", "", "my address")


        // val shopEntityDAO = EntityDAO(dbHelper)

        //val id = shopEntityDAO.insert(shop)

     //   Assert.assertTrue(id > 0)
    }
}
