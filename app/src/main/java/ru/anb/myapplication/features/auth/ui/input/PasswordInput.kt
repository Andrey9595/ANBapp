package ru.anb.myapplication.features.auth.ui.input

import android.content.Context
import android.util.AttributeSet
import ru.anb.myapplication.R

class PasswordInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : CustomInputLayout(context, attrs, defStyleAttr) {
    override val errorMessageId: Int = R.string.password_length_error
    
    override fun innerIsValid(): Boolean {
        return text().length > 5
    }
}