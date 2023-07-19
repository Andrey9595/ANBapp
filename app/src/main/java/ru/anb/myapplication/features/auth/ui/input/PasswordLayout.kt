package ru.anb.myapplication.features.auth.ui.input

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import ru.anb.myapplication.R
import ru.anb.myapplication.databinding.PasswordLayoutBinding

class PasswordLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : CustomInputLayout(context, attrs, defStyleAttr) {
    
    private val binding: PasswordLayoutBinding
    
    init {
        binding = PasswordLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    override val errorMessageId: Int = R.string.password_error
    
    override fun isValid(): Boolean {
        val isValid = innerIsValid()
        binding.errorText.text = if (isValid) "" else context.getString(errorMessageId)
        return isValid
    }
    
    override fun innerIsValid(): Boolean {
        binding.password.isValid()
        binding.checkPassword.isValid()
        return binding.password.text() == binding.checkPassword.text()
    }
}