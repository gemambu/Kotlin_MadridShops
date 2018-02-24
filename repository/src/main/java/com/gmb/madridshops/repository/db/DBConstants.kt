package com.gmb.madridshops.repository.db

import android.icu.text.DateTimePatternGenerator.PatternInfo.CONFLICT

object DBConstants {
    val TABLE_ENTITY = "ENTITY"

    // Table field constants
    val KEY_ENTITY_DATABASE_ID = "_id"
    val KEY_ENTITY_ID_JSON = "ID_JSON"
    val KEY_ENTITY_NAME = "NAME"
    val KEY_ENTITY_IMAGE_URL = "IMAGE_URL"
    val KEY_ENTITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    val KEY_ENTITY_ADDRESS = "ADDRESS"
    private val KEY_ENTITY_URL = "URL"
    val KEY_ENTITY_DESCRIPTION_EN = "DESCRIPTION_EN"
    val KEY_ENTITY_DESCRIPTION_ES = "DESCRIPTION_ES"
    val KEY_ENTITY_OPENING_HOURS_EN = "OPENING_HOURS_EN"
    val KEY_ENTITY_OPENING_HOURS_ES = "OPENING_HOURS_ES"

    val KEY_ENTITY_LATITUDE = "LATITUDE"
    val KEY_ENTITY_LONGITUDE = "LONGITUDE"

    val KEY_ENTITY_TYPE = "TYPE"

    val ALL_COLUMNS = arrayOf(
            KEY_ENTITY_DATABASE_ID,
            KEY_ENTITY_ID_JSON,
            KEY_ENTITY_NAME,
            KEY_ENTITY_IMAGE_URL,
            KEY_ENTITY_LOGO_IMAGE_URL,
            KEY_ENTITY_ADDRESS,
            KEY_ENTITY_URL,
            KEY_ENTITY_LATITUDE,
            KEY_ENTITY_LONGITUDE,
            KEY_ENTITY_DESCRIPTION_EN,
            KEY_ENTITY_DESCRIPTION_ES,
            KEY_ENTITY_OPENING_HOURS_EN,
            KEY_ENTITY_OPENING_HOURS_ES,
            KEY_ENTITY_TYPE)

    private val SQL_SCRIPT_CREATE_ENTITY_TABLE = (
            "create table " + TABLE_ENTITY
                    + "( "
                    + KEY_ENTITY_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_ENTITY_ID_JSON + " integer, "
                    + KEY_ENTITY_NAME + " text not null,"
                    + KEY_ENTITY_IMAGE_URL + " text, "
                    + KEY_ENTITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ENTITY_ADDRESS + " text,"
                    + KEY_ENTITY_URL + " text,"
                    + KEY_ENTITY_LATITUDE + " real,"
                    + KEY_ENTITY_LONGITUDE + " real, "
                    + KEY_ENTITY_DESCRIPTION_EN + " text, "
                    + KEY_ENTITY_DESCRIPTION_ES + " text, "
                    + KEY_ENTITY_OPENING_HOURS_EN + " text, "
                    + KEY_ENTITY_OPENING_HOURS_ES + " text, "
                    + KEY_ENTITY_TYPE + " text"
                    //+ " ,UNIQUE ("+ KEY_ENTITY_NAME + "," + KEY_ENTITY_ADDRESS +") ON CONFLICT REPLACE"
                    + ");")

    val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_ENTITY_TABLE)
}