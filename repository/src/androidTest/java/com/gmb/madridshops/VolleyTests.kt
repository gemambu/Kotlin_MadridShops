package com.gmb.madridshops

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.gmb.madridshops.repository.ErrorCompletion
import com.gmb.madridshops.repository.SuccessCompletion
import com.gmb.madridshops.repository.network.GetJsonManager
import com.gmb.madridshops.repository.network.GetJsonManagerVolleyImpl
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4::class)
class VolleyTests {

    // Context of the app under test.
    private val appContext = InstrumentationRegistry.getTargetContext()


    @Test
    fun given_valid_url_it_gets_non_null_json_as_string() {

        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(appContext)

        jsonManager.execute(url, object : SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                assertTrue(e.isNotEmpty())
            }
        },
                object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        assertTrue(false)
                    }
                })


    }
}