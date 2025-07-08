package org.itsolutions.mydivelog.configuration

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.itsolutions.mydivelog.data.local.converters.DateTimeConverter
import org.itsolutions.mydivelog.data.local.converters.DiveOrganizationConverter
import org.itsolutions.mydivelog.data.local.database.MyDiveLogDatabase
import org.itsolutions.mydivelog.data.local.database.dao.CertificateDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDiveLogDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = MyDiveLogDatabase::class.java,
                name = "my_dive_log_database"
            )
            .addTypeConverter(DateTimeConverter())
            .addTypeConverter(DiveOrganizationConverter())
            .build()
    }

    @Provides
    fun provideCertificateDao(database: MyDiveLogDatabase): CertificateDao =
        database.certificateDao()
}