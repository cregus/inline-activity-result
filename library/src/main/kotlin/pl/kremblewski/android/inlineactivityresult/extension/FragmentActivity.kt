package pl.kremblewski.android.inlineactivityresult.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import pl.kremblewski.android.inlineactivityresult.ActivityResultListener
import pl.kremblewski.android.inlineactivityresult.InlineActivityResultFragment

fun FragmentActivity.startActivityForResult(
    intent: Intent,
    listener: ActivityResultListener
) {
    InlineActivityResultFragment.get(supportFragmentManager).start(intent, listener)
}

inline fun FragmentActivity.startActivityForResultOk(
    intent: Intent,
    crossinline listener: ActivityResultListener
) {
    startActivityForResult(intent) { result -> result.takeIfIsOk()?.run(listener) }
}

inline fun <reified A : Activity> FragmentActivity.startActivityForResult(
    extras: Bundle? = null,
    flags: Int = 0,
    noinline listener: ActivityResultListener
) {
    startActivityForResult(intentForActivity<A>(extras, flags), listener)
}

inline fun <reified A : Activity> FragmentActivity.startActivityForResultOk(
    extras: Bundle? = null,
    flags: Int = 0,
    noinline listener: ActivityResultListener
) {
    startActivityForResultOk(intentForActivity<A>(extras, flags), listener)
}