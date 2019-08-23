package pl.kremblewski.android.inlineactivityresult

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.concurrent.atomic.AtomicInteger

internal class InlineActivityResultFragment : Fragment() {
    companion object {
        private val FRAGMENT_TAG = InlineActivityResultFragment::class.java.name

        fun get(fragmentManager: FragmentManager): InlineActivityResultFragment {
            return fragmentManager.findFragmentByTag(FRAGMENT_TAG) as? InlineActivityResultFragment
                ?: init(fragmentManager)
        }

        private fun init(fragmentManager: FragmentManager): InlineActivityResultFragment {
            return InlineActivityResultFragment().also { fragment ->
                fragmentManager.beginTransaction()
                    .add(fragment, FRAGMENT_TAG)
                    .commitAllowingStateLoss()
                fragmentManager.executePendingTransactions()
            }
        }
    }

    private val _requestCode = AtomicInteger(100)
    private val _resultListeners = SparseArray<ActivityResultListener>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        _resultListeners[requestCode]?.invoke(ActivityResult(resultCode, data))
        _resultListeners.remove(requestCode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun start(intent: Intent, listener: ActivityResultListener) {
        val requestCode = _requestCode.incrementAndGet()
        _resultListeners.put(requestCode, listener)
        startActivityForResult(intent, requestCode)
    }
}