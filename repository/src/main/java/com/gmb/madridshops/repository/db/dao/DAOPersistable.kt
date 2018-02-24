package com.gmb.madridshops.repository.db.dao

import android.database.Cursor

interface DAOReadOps<T> {
    fun query(id: Long): T
    fun query(): List<T>
    fun query(type: String): List<T>
    fun queryCursor(id: Long): Cursor
}

interface DAOWirteOps<T> {
    fun insert(element: T, type: String): Long
    fun update(id: Long, element: T): Long

    /**
     * Deletes the element passed from DB
     */
    fun delete(element: T): Long

    /**
     * deletes the element with id from DB. It must start in 1
     */
    fun delete(id: Long): Long

    fun deleteAll(): Boolean
}

interface DAOPersistable<T> : DAOReadOps<T>, DAOWirteOps<T>