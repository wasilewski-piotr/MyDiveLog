package org.itsolutions.mydivelog.view.screens.homepage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.itsolutions.mydivelog.utils.activity.setDiveLogTheme

class HomepageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDiveLogTheme {
            Box(modifier = Modifier.size(60.dp).background(color = Color.Red)) {
                Text("Homepage")
            }
        }
    }

    companion object {
        fun createInstance(context: Context) = Intent(context, HomepageActivity::class.java)
    }
}