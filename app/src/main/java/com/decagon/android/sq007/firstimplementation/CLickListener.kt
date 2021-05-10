package com.decagon.android.sq007.firstimplementation

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.ViewTarget

interface CLickListener {
    fun onItemClicked(position: Int)
}