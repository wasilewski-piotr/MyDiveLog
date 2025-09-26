package org.itsolutions.mydivelog.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import org.itsolutions.mydivelog.R

@Serializable
enum class DiveOrganization(
    @StringRes val longName: Int,
    @DrawableRes val logo: Int? = null,
    val backgroundColor: Color? = null,
) {
    SSI(R.string.SSI, R.drawable.ssi_logo, Color(0xFFE42313)),
    PADI(R.string.PADI, R.drawable.padi_logo, Color.Black),
    GUE(R.string.GUE)
}