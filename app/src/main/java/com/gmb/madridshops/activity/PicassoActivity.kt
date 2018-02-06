package com.gmb.madridshops.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.gmb.madridshops.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        // esto debe ir a nivel de APP, para toda la gestion de imagenes
        Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this).isLoggingEnabled = true

        Picasso
                .with(this)
                .load("http://stillcracking.com/wp-content/uploads/2014/05/c5731d46405fb1226cad6eef30c99ce145e7894eb7948b4180e78e0b3a32aaca_1.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img1)
        Picasso
                .with(this)
                .load("https://i.imgur.com/KWl6pqT.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img2)
        Picasso
                .with(this)
                .load("http://www.abc.es/media/play/2017/05/11/deadpool-kcO--620x349@abc.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img3)

    }

}
