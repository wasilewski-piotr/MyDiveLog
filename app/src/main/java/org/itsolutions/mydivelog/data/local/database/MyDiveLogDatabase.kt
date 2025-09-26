package org.itsolutions.mydivelog.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.itsolutions.mydivelog.data.local.converters.DateTimeConverter
import org.itsolutions.mydivelog.data.local.converters.DiveOrganizationConverter
import org.itsolutions.mydivelog.data.local.database.dao.CertificateDao
import org.itsolutions.mydivelog.data.local.database.entity.CertificateEntity

@Database(
    entities = [CertificateEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class, DiveOrganizationConverter::class)
abstract class MyDiveLogDatabase : RoomDatabase() {
    abstract fun certificateDao(): CertificateDao
}