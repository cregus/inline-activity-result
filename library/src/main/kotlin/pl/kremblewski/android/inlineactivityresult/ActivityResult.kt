package pl.kremblewski.android.inlineactivityresult

import android.app.Activity
import android.content.Intent

class ActivityResult(
    val resultCode: Int,
    val intent: Intent?
) {
    fun isOk() = resultCode == Activity.RESULT_OK
    fun isCancelled() = resultCode == Activity.RESULT_CANCELED
    fun isFirstUser() = resultCode == Activity.RESULT_FIRST_USER
    fun takeIfIsOk() = takeIf { isOk() }
    fun takeIfIsCancelled() = takeIf { isCancelled() }
    fun takeIfIsFirstUser() = takeIf { isFirstUser() }
}