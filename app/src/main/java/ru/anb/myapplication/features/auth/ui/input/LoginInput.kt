package ru.anb.myapplication.features.auth.ui.input

import android.content.Context
import android.util.AttributeSet
import ru.anb.myapplication.R

class LoginInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : CustomInputLayout(context, attrs, defStyleAttr) {
    override val errorMessageId = R.string.login_error
    
    override fun innerIsValid(): Boolean {
        return text().length > 1
    }
}