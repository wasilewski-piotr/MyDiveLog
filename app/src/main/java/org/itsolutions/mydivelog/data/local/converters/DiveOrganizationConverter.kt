package org.itsolutions.mydivelog.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.itsolutions.mydivelog.domain.model.DiveOrganization

@ProvidedTypeConverter
class DiveOrganizationConverter {
    @TypeConverter
    fun fromDiveOrganization(organization: DiveOrganization?): String? = organization?.name

    @TypeConverter
    fun toDiveOrganization(organization: String?): DiveOrganization? =
        organization?.let { DiveOrganization.valueOf(it) }
}