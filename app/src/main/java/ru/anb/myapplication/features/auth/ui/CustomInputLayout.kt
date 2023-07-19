package ru.anb.myapplication.features.auth.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

abstract class CustomInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {


}