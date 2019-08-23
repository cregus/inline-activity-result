package pl.kremblewski.android.inlineactivityresult.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import pl.kremblewski.android.inlineactivityresult.ActivityResultListener
import pl.kremblewski.android.inlineactivityresult.InlineActivityResultFragment

fun Fragment.startActivityForResult(
    intent: Intent,
    listener: ActivityResultListener
) {
    InlineActivityResultFragment.get(requireActivity().supportFragmentManager).start(intent, listener)
}

inline fun Fragment.startActivityForResultOk(
    intent: Intent,
    crossinline listener: ActivityResultListener
) {
    startActivityForResult(intent) { result -> result.takeIfIsOk()?.run(listener) }
}

inline fun <reified A : Activity> Fragment.startActivityForResult(
    extras: Bundle? = null,
    flags: Int = 0,
    noinline listener: ActivityResultListener
) {
    startActivityForResult(requireContext().intentForActivity<A>(extras, flags), listener)
}

inline fun <reified A : Activity> Fragment.startActivityForResultOk(
    extras: Bundle? = null,
    flags: Int = 0,
    noinline listener: ActivityResultListener
) {
    startActivityForResultOk(requireContext().intentForActivity<A>(extras, flags), listener)
}