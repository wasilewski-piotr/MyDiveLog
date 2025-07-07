package org.itsolutions.mydivelog.view.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import org.itsolutions.mydivelog.view.screens.homepage.HomepageActivity

class MyDiveLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(HomepageActivity.createInstance(this))
        finish()
    }
}