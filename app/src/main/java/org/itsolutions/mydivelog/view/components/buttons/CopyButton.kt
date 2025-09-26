package org.itsolutions.mydivelog.view.components.buttons

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.itsolutions.mydivelog.R
import org.itsolutions.mydivelog.view.theme.DialogThemeInverted

@Composable
fun CopyButton(
    textToCopy: String,
    modifier: Modifier = Modifier,
    initialText: String = "Copy",
    copiedText: String = "Copied",
    resetDelay: Long = 3000L,
) {
    DialogThemeInverted {
        val context = LocalContext.current
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", textToCopy)
        var isCopied by remember { mutableStateOf(false) }

        LaunchedEffect(isCopied) {
            if (isCopied) {
                delay(resetDelay)
                isCopied = false
            }
        }

        Button(
            onClick = {
                clipboard.setPrimaryClip(clip)
                isCopied = true
            },
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.height(32.dp),
        ) {
            val icon = painterResource(if (isCopied) R.drawable.check else R.drawable.content_copy)
            val label = if (isCopied) copiedText else initialText

            Icon(
                painter = icon,
                contentDescription = label,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(label, style = MaterialTheme.typography.labelSmall)
        }
    }
}