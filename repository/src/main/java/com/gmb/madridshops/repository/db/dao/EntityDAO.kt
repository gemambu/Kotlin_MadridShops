package com.gmb.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.gmb.madridshops.repository.db.DBConstants
import com.gmb.madridshops.repository.db.DBHelper
import com.gmb.madridshops.repository.model.EntityData


class EntityDAO(private val dbHelper: DBHelper) : DAOPersistable<EntityData> {

    private val dbReadOnlyConn: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteOnlyConn: SQLiteDatabase = dbHelper.writableDatabase

    private fun contentValues(entityData: EntityData, type: String): ContentValues {
        val content = ContentValues()

        val latitude = entityData.latitude
        val longitude = entityData.longitude

        content.put(DBConstants.KEY_ENTITY_ID_JSON, entityData.id)
        content.put(DBConstants.KEY_ENTITY_NAME, entityData.name)
        content.put(DBConstants.KEY_ENTITY_DESCRIPTION_EN, entityData.description_en)
        content.put(DBConstants.KEY_ENTITY_DESCRIPTION_ES, entityData.description_es)
        content.put(DBConstants.KEY_ENTITY_LATITUDE, latitude)
        content.put(DBConstants.KEY_ENTITY_LONGITUDE, longitude)
        content.put(DBConstants.KEY_ENTITY_IMAGE_URL, entityData.image)
        content.put(DBConstants.KEY_ENTITY_LOGO_IMAGE_URL, entityData.logo)
        content.put(DBConstants.KEY_ENTITY_ADDRESS, entityData.address)
        content.put(DBConstants.KEY_ENTITY_OPENING_HOURS_EN, entityData.openingHours_en)
        content.put(DBConstants.KEY_ENTITY_OPENING_HOURS_ES, entityData.openingHours_es)
        content.put(DBConstants.KEY_ENTITY_TYPE, type)
        return content
    }

    override fun query(id: Long): EntityData {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        return entityFromCursor(cursor)!!
    }

    override fun query(): List<EntityData> {
        val result = ArrayList<EntityData>()

        val cursor = dbReadOnlyConn.query(DBConstants.TABLE_ENTITY,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_ENTITY_DATABASE_ID)

        while (cursor.moveToNext()) {
            val entity = entityFromCursor(cursor)!!
            result.add(entity)

        }


        return result
    }

    private fun entityFromCursor(cursor: Cursor): EntityData? {

        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }
        return EntityData(cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DATABASE_ID)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ENTITY_ID_JSON)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_TYPE))
        )
    }

    override fun queryCursor(id: Long): Cursor = dbReadOnlyConn.query(DBConstants.TABLE_ENTITY,
            DBConstants.ALL_COLUMNS,
            DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
            arrayOf(id.toString()),
            "",
            "",
            DBConstants.KEY_ENTITY_DATABASE_ID)


    override fun insert(element: EntityData, type: String): Long = dbReadWriteOnlyConn.insert(DBConstants.TABLE_ENTITY, null, contentValues(element, type))

    override fun update(id: Long, element: EntityData): Long =
            dbReadWriteOnlyConn.update(
                    DBConstants.TABLE_ENTITY,
                    contentValues(element, element.type),
                    DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
                    arrayOf(id.toString())).toLong()


    override fun delete(element: EntityData): Long = if (element.databaseId < 0) 0 else delete(element.databaseId)


    override fun delete(id: Long): Long = dbReadWriteOnlyConn.delete(DBConstants.TABLE_ENTITY,
            DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
            arrayOf(id.toString())).toLong()

    override fun deleteAll(): Boolean = dbReadWriteOnlyConn.delete(DBConstants.TABLE_ENTITY,
            null,
            null).toLong() >= 0


}