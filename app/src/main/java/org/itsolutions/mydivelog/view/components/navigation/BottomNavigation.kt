package org.itsolutions.mydivelog.view.components.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.itsolutions.mydivelog.presentation.menu.buddies.BuddiesViewModel
import org.itsolutions.mydivelog.presentation.menu.certifications.CertificationsViewModel
import org.itsolutions.mydivelog.presentation.menu.home.HomeViewModel
import org.itsolutions.mydivelog.presentation.menu.myDives.MyDivesViewModel
import org.itsolutions.mydivelog.presentation.menu.statistics.StatisticsViewModel
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.utils.modifier.windowHorizontalPadding
import org.itsolutions.mydivelog.view.screens.menu.buddies.BuddiesScreen
import org.itsolutions.mydivelog.view.screens.menu.certifications.CertificationsScreen
import org.itsolutions.mydivelog.view.screens.menu.home.HomeScreen
import org.itsolutions.mydivelog.view.screens.menu.myDives.MyDivesScreen
import org.itsolutions.mydivelog.view.screens.menu.statistics.StatisticsScreen

@Composable
private fun BottomNavigationNavigationHost(
    navController: NavHostController,
    startDestination: BottomBarNavigationElement,
    onNavigateToScreen: (BottomBarNavigationElement) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination.route, modifier) {
        BottomBarNavigationElement.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    BottomBarNavigationElement.Home -> {
                        val viewModel: HomeViewModel = hiltViewModel()
                        HomeScreen(viewModel)
                    }

                    BottomBarNavigationElement.Certifications -> {
                        val viewModel: CertificationsViewModel = hiltViewModel()
                        CertificationsScreen(viewModel)
                    }

                    BottomBarNavigationElement.MyDives -> {
                        val viewModel: MyDivesViewModel = hiltViewModel()
                        MyDivesScreen(viewModel) {
                            onNavigateToScreen(BottomBarNavigationElement.Statistics)
                        }
                    }

                    BottomBarNavigationElement.Buddies -> {
                        val viewModel: BuddiesViewModel = hiltViewModel()
                        BuddiesScreen(viewModel)
                    }

                    BottomBarNavigationElement.Statistics -> {
                        val viewModel: StatisticsViewModel = hiltViewModel()
                        StatisticsScreen(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun DiveLogBottomNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = BottomBarNavigationElement.Home
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val currentRoute = currentDestination?.hierarchy?.firstOrNull()?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                NavigationBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = AppSpacing.sm),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BottomBarNavigationElement.entries.forEach {
                            NavigationBarItem(
                                selected = currentRoute == it.route,
                                onClick = { navController.navigateBackToHomepage(it) },
                                icon = {
                                    Icon(
                                        painter = painterResource(it.icon),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(it.label)) }
                            )
                        }
                    }
                }
            }
        }
    ) { contentPadding ->
        BottomNavigationNavigationHost(
            navController = navController,
            startDestination = startDestination,
            onNavigateToScreen = { navController.navigateBackToHomepage(it) },
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .windowHorizontalPadding()
        )
    }
}

private fun NavHostController.navigateBackToHomepage(route: BottomBarNavigationElement) {
    navigate(route.route) {
        popUpTo(graph.startDestinationId) {
            inclusive = false
        }
        launchSingleTop = true
    }
}