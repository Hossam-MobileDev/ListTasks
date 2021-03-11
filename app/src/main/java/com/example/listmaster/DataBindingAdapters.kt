

package com.example.listmaster

import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.TextView

object DataBindingAdapters {

  @JvmStatic
  @BindingAdapter("android:highlight_tint")
  fun setHighlightTint(textView: TextView, colorResourceId: Int) {
    Log.i("color filter", colorResourceId.toString())
    textView.background.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(textView.context,
        colorResourceId), PorterDuff.Mode.SRC)
  }
}