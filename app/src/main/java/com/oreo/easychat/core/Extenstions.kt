package com.oreo.easychat.core

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


fun Context.showToast(message: String) {
    Toast.makeText(this, "$message ", Toast.LENGTH_LONG).show()
}


fun Fragment.copyToClipboard(text: String) {
    val clipboard: ClipboardManager =
        requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    Toast.makeText(requireContext(), "$text ", Toast.LENGTH_LONG).show()
    clipboard.setPrimaryClip(clip)
}


fun View.hideKeyboard(context: Context?) {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun ImageView.loadImgFromUrl(context: Context, url: String, imageViewId: ImageView) {
    Glide.with(context).load(url).into(imageViewId)
}

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

