package org.itsolutions.mydivelog.view.screens

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import org.itsolutions.mydivelog.utils.activity.setDiveLogTheme
import org.itsolutions.mydivelog.view.components.navigation.BottomNavigationBar

@HiltAndroidApp
class MyDiveLogApplication : Application()

@AndroidEntryPoint
class MyDiveLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false
        setDiveLogTheme {
            BottomNavigationBar()
        }
    }
}