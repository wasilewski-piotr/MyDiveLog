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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.itsolutions.mydivelog.presentation.buddies.BuddiesViewModel
import org.itsolutions.mydivelog.presentation.certificates.CertificatesViewModel
import org.itsolutions.mydivelog.presentation.home.HomeViewModel
import org.itsolutions.mydivelog.presentation.myDives.MyDivesViewModel
import org.itsolutions.mydivelog.presentation.statistics.StatisticsViewModel
import org.itsolutions.mydivelog.utils.AppSpacing
import org.itsolutions.mydivelog.utils.modifier.windowHorizontalPadding
import org.itsolutions.mydivelog.view.screens.buddies.BuddiesScreen
import org.itsolutions.mydivelog.view.screens.certificates.CertificatesScreen
import org.itsolutions.mydivelog.view.screens.home.HomeScreen
import org.itsolutions.mydivelog.view.screens.myDives.MyDivesScreen
import org.itsolutions.mydivelog.view.screens.statistics.StatisticsScreen

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
                    BottomBarNavigationElement.Certificates -> {
                        val viewModel: CertificatesViewModel = hiltViewModel()
                        CertificatesScreen(viewModel)
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
    var selectedDestination by rememberSaveable { mutableStateOf(startDestination) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                NavigationBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = AppSpacing.md),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BottomBarNavigationElement.entries.forEachIndexed { index, destination ->
                            NavigationBarItem(
                                selected = selectedDestination.ordinal == index,
                                onClick = {
                                    navController.navigate(route = destination.route)
                                    selectedDestination = destination
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(destination.icon),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(destination.label)) }
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
            onNavigateToScreen = {
                selectedDestination = it
                navController.navigate(it.route)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .windowHorizontalPadding()
        )
    }
}