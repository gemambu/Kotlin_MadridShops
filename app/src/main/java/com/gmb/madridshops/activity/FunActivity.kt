package com.gmb.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import com.gmb.madridshops.R
import com.gmb.madridshops.util.`fun`.quotes
import kotlinx.android.synthetic.main.activity_fun.*
import java.util.*

class FunActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun)

        displayToolbar()

        fun_activity_description.text = getString(R.string.fun_activity_description)

        fun_activity_button.setOnClickListener {
            val random = Random().nextInt(quotes.size)
            fun_activity_quote.text = quotes[random]
        }
    }

    private fun displayToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val title = findViewById<TextView>(R.id.toolbar_title)
        setSupportActionBar(toolbar)

        title.text = getString(R.string.fun_time_title)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // se ha pulsado la flecha de back
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
