package ru.anb.myapplication.features.home.ui.job

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentNewJobBinding
import ru.anb.myapplication.features.home.domain.model.job.JobCreateRequest
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class NewJobFragment : BaseFragment<FragmentNewJobBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentNewJobBinding =
        { inflater, container ->
            FragmentNewJobBinding.inflate(inflater, container, false)
        }

    private val viewModel: NewJobViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.jobCreateState.collect {
                    binding.progress.progress.isVisible = it is LoadState.Loading
                    when (it) {
                        is LoadState.Success -> findNavController().navigateUp()
                        is LoadState.Error -> Toast.makeText(
                            requireContext(),
                            it.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


        binding.startDateBtn.setOnClickListener {
            showDatePicker(binding.startJobEditText)
        }
        binding.endDateBtn.setOnClickListener {
            showDatePicker(binding.endJobEditText)
        }

        binding.newJobAppBar.setOnMenuItemClickListener { menu ->
            val jobText = binding.jobEditText.text.toString()
            val position = binding.positionEditText.text.toString()
            val web = binding.linkEditText.text.toString()
            val newCreateRequest = JobCreateRequest(
                (1L..100000L).random(), jobText, position, binding.startJobEditText.text.toString(),
                binding.endJobEditText.text.toString(), web
            )
            viewModel.create(newCreateRequest)
            true
        }

        binding.newJobAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showDatePicker(editText: EditText) {
        val const =
            CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.now()).build()
        val datePicker =
            MaterialDatePicker.Builder.datePicker().setCalendarConstraints(const).build()
        datePicker.addOnPositiveButtonClickListener {
            val date = LocalDateTime.ofEpochSecond(it / 1000, 0, ZoneOffset.UTC)
            editText.setText(date.format(DateTimeFormatter.ISO_DATE))
        }
        datePicker.show(parentFragmentManager, "show")
    }
}

