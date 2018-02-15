package com.gmb.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.gmb.madridshops.repository.db.DBConstants
import com.gmb.madridshops.repository.db.DBHelper
import com.gmb.madridshops.repository.model.ShopEntity


class ShopDAO(private val dbHelper: DBHelper) : DAOPersistable<ShopEntity> {

    private val dbReadOnlyConn: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteOnlyConn: SQLiteDatabase = dbHelper.writableDatabase

    private fun contentValues(shopEntity: ShopEntity): ContentValues {
        val content = ContentValues()

        val latitude = shopEntity.latitude
        val longitude = shopEntity.longitude

        content.put(DBConstants.KEY_SHOP_ID_JSON, shopEntity.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_EN, shopEntity.description_en)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_ES, shopEntity.description_es)
        content.put(DBConstants.KEY_SHOP_LATITUDE, latitude)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, longitude)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.image)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_EN, shopEntity.openingHours_en)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_ES, shopEntity.openingHours_es)

        return content
    }

    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        return entityFromCursor(cursor)!!
    }

    override fun query(): List<ShopEntity> {
        val result = ArrayList<ShopEntity>()

        val cursor = dbReadOnlyConn.query(DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID)

        while (cursor.moveToNext()) {
            val entity = entityFromCursor(cursor)!!
            result.add(entity)

        }


        return result
    }

    private fun entityFromCursor(cursor: Cursor): ShopEntity? {

        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }
        return ShopEntity(cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_DATABASE_ID)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_ID_JSON)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_ADDRESS))
        )
    }

    override fun queryCursor(id: Long): Cursor = dbReadOnlyConn.query(DBConstants.TABLE_SHOP,
            DBConstants.ALL_COLUMNS,
            DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
            arrayOf(id.toString()),
            "",
            "",
            DBConstants.KEY_SHOP_DATABASE_ID)


    override fun insert(element: ShopEntity): Long = dbReadWriteOnlyConn.insert(DBConstants.TABLE_SHOP, null, contentValues(element))

    override fun update(id: Long, element: ShopEntity): Long =
            dbReadWriteOnlyConn.update(
                    DBConstants.TABLE_SHOP,
                    contentValues(element),
                    DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
                    arrayOf(id.toString())).toLong()


    override fun delete(element: ShopEntity): Long = if (element.databaseId < 0) 0 else delete(element.databaseId)


    override fun delete(id: Long): Long = dbReadWriteOnlyConn.delete(DBConstants.TABLE_SHOP,
            DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
            arrayOf(id.toString())).toLong()

    override fun deleteAll(): Boolean = dbReadWriteOnlyConn.delete(DBConstants.TABLE_SHOP,
            null,
            null).toLong() >= 0


}