package pl.kremblewski.android.inlineactivityresult.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

inline fun <reified A : Activity> Context.intentForActivity(
    extras: Bundle? = null,
    flags: Int = 0
): Intent {
    return Intent(this, A::class.java).apply {
        setFlags(flags)
        if (extras != null) {
            replaceExtras(extras)
        }
    }
}