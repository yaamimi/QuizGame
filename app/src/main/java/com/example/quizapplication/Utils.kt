package com.example.quizapplication

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity


class Utils {
    companion object {
        fun saveAtSharedPrefs(activity: Activity, name: String) {
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString(activity.getString(R.string.name_key), name)
                apply()
            }
        }

        fun readFromSharedPrefs(activity: Activity): String {
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            return sharedPref.getString(activity.getString(R.string.name_key), "").toString()
        }
    }

}