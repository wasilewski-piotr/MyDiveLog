package org.itsolutions.mydivelog.view.screens

import android.app.Application
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import org.itsolutions.mydivelog.utils.activity.setDiveLogTheme
import org.itsolutions.mydivelog.view.components.navigation.DiveLogBottomNavigation

@HiltAndroidApp
class MyDiveLogApplication : Application()

@AndroidEntryPoint
class MyDiveLogActivity : MyDiveLogComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDiveLogTheme {
            DiveLogBottomNavigation()
        }
    }
}