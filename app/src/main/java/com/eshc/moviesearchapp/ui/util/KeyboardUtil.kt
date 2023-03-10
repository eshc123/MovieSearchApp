package com.eshc.moviesearchapp.ui.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object KeyboardUtil {
    fun softKeyboardHide(context: Context, edittext : EditText?) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(edittext?.windowToken, 0)
    }
}