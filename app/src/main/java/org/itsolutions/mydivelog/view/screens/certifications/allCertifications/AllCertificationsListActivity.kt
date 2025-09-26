package org.itsolutions.mydivelog.view.screens.certifications.allCertifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.itsolutions.mydivelog.utils.activity.setDiveLogTheme
import org.itsolutions.mydivelog.view.screens.MyDiveLogComponentActivity

@AndroidEntryPoint
class AllCertificationsListActivity : MyDiveLogComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDiveLogTheme {
            AllCertificationsListScreen(
                onBack = ::finish,
                onReload = { setResult(RESULT_OK) }
            )
        }
    }

    companion object {
        fun createInstance(context: Context) =
            Intent(context, AllCertificationsListActivity::class.java)
    }
}