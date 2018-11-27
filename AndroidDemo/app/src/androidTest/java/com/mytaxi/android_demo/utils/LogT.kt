package com.mytaxi.android_demo.utils

import android.util.Log

/**
 * Simple wrapper around android Logger for logging test activities
 */
class LogT {
    companion object {
        private const val TAG = "Espresso"

        fun v(message: String) {
            Log.v(TAG, message)
        }

        fun d(message: String) {
            Log.d(TAG, message)
        }

        fun i(message: String) {
            Log.v(TAG, message)
        }

        fun e(message: String) {
            Log.e(TAG, message)
        }
    }
}