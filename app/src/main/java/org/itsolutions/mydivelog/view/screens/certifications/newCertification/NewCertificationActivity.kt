package org.itsolutions.mydivelog.view.screens.certifications.newCertification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.itsolutions.mydivelog.presentation.certifications.NewCertificationViewModel
import org.itsolutions.mydivelog.utils.activity.setDiveLogTheme
import org.itsolutions.mydivelog.view.screens.MyDiveLogComponentActivity

@AndroidEntryPoint
class NewCertificationActivity : MyDiveLogComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDiveLogTheme {
            val viewModel: NewCertificationViewModel = hiltViewModel()
            val navHostController = rememberNavController()
            NewCertificationNavHost(viewModel, navHostController) {
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    companion object {
        fun createInstance(context: Context) =
            Intent(context, NewCertificationActivity::class.java)
    }
}