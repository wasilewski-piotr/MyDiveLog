package org.itsolutions.mydivelog.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.itsolutions.mydivelog.data.local.database.entity.CertificateEntity
import org.itsolutions.mydivelog.domain.model.DiveOrganization

@Dao
interface CertificateDao {

    @Query("SELECT DISTINCT organization FROM certifications")
    suspend fun getDistinctOrganizations(): List<DiveOrganization>

    @Query("SELECT * FROM certifications")
    suspend fun getAllCertifications(): List<CertificateEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.ABORT)
    suspend fun createCertificate(certificate: CertificateEntity)

    @Delete
    suspend fun deleteCertificate(certificate: CertificateEntity)
}