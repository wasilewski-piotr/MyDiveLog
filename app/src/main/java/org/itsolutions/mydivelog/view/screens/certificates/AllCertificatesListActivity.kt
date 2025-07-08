package org.itsolutions.mydivelog.view.screens.certificates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.itsolutions.mydivelog.utils.activity.setDiveLogTheme
import org.itsolutions.mydivelog.view.screens.MyDiveLogComponentActivity

@AndroidEntryPoint
class AllCertificatesListActivity : MyDiveLogComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDiveLogTheme {
            AllCertificatesListScreen(
                onBackPressed = ::finish
            )
        }
    }

    companion object {
        fun createInstance(context: Context) =
            Intent(context, AllCertificatesListActivity::class.java)
    }
}