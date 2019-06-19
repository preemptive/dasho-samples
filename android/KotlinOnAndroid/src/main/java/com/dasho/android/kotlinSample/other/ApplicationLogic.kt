package com.dasho.android.kotlinSample.other

import android.content.Context

/**
 * This class is only provided to allow checking at runtime to verify that DashO and R8 were used in
 * the build process. It is modeled after the ApplicationLogic class used in the check-specific
 * Android samples.
 */
class ApplicationLogic constructor(private val context:Context) {
    companion object {
        // This flag is only used to verify that DashO has been run correctly.
        private var injectionApplied:Boolean = false

        /**
         * Used by a check just to report that DashO injection was applied correctly.
         * @param ignored an unused check result
         */
        @Suppress("unused", "UNUSED_PARAMETER") // only referenced in the DashO config
        @JvmStatic
        fun setupInjectionWasApplied(ignored:Boolean) {
            injectionApplied = true
        }

        fun wasDashOUsed():Boolean {
            return injectionApplied
        }

        fun wasRenamingApplied():Boolean {
            return try {
                // Prevent R8 from recognizing and unintentionally "fixing" this string by replacing it with the class's
                // new name.
                Class.forName("xcom.dasho.android.kotlinSample.other.ApplicationLogic".substring(1))
                false
            } catch (exception:ClassNotFoundException) {
                true
            }
        }
    }

    @Suppress("unused") // Used by the check code.
    fun getApplicationContext():Context {
        return context
    }

    @Suppress("unused") // only referenced in the DashO config
    fun someApplicationLogic() {
    }
}
