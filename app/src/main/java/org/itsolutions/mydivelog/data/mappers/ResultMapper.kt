package org.itsolutions.mydivelog.data.mappers

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteFullException
import org.itsolutions.mydivelog.data.local.database.entity.CertificateEntity
import org.itsolutions.mydivelog.domain.model.Certificate
import org.itsolutions.mydivelog.domain.model.results.DataError
import org.itsolutions.mydivelog.domain.model.results.Result
import kotlin.reflect.KClass

inline fun <T, C: Any> databaseCall(classType: KClass<C>, block: () -> T): Result<T, DataError> =
    try {
        Result.Success(block())
    } catch (e: SQLiteConstraintException) {
        when (classType) {
            Certificate::class -> Result.Error(DataError.Local.CERTIFICATE_ALREADY_EXISTS)
            else -> Result.Error(DataError.Local.ELEMENT_ALREADY_EXISTS)
        }
    } catch (e: SQLiteFullException) {
        Result.Error(DataError.Local.DISK_FULL)
    } catch (e: Exception) {
        Result.Error(DataError.Local.UNKNOWN)
    }

