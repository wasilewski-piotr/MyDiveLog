package org.itsolutions.mydivelog.data.mappers

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteFullException
import org.itsolutions.mydivelog.domain.model.results.DataError
import org.itsolutions.mydivelog.domain.model.results.Result

inline fun <T> databaseCall(block: () -> T): Result<T, DataError> =
    try {
        Result.Success(block())
    } catch (e: SQLiteConstraintException) {
        Result.Error(DataError.Local.ELEMENT_ALREADY_EXISTS)
    } catch (e: SQLiteFullException) {
        Result.Error(DataError.Local.DISK_FULL)
    } catch (e: Exception) {
        Result.Error(DataError.Local.UNKNOWN)
    }

