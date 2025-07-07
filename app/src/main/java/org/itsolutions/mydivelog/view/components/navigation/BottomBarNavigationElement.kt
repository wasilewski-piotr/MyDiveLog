package org.itsolutions.mydivelog.view.components.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.itsolutions.mydivelog.R

enum class BottomBarNavigationElement(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    Home(
        route = "home",
        label = R.string.bottom_navigation_home,
        icon = R.drawable.home
    ),
    Certificates(
        route = "certificates",
        label = R.string.bottom_navigation_certificates,
        icon = R.drawable.badge
    ),
    MyDives(
        route = "my_dives",
        label = R.string.bottom_navigation_my_dives,
        icon = R.drawable.scuba_diving
    ),
    Buddies(
        route = "buddies",
        label = R.string.bottom_navigation_buddies,
        icon = R.drawable.emoji_people
    ),
    Statistics(
        route = "statistics",
        label = R.string.bottom_navigation_statistics,
        icon = R.drawable.bar_chart
    ),
}

