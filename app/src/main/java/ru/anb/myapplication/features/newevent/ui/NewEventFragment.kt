package ru.anb.myapplication.features.newevent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.core.ui.BaseFragment
import ru.anb.myapplication.databinding.FragmentNewEventBinding
import ru.anb.myapplication.features.home.domain.model.EventCreateRequest
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class NewEventFragment : BaseFragment<FragmentNewEventBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?) -> FragmentNewEventBinding =
        { inflater, container -> FragmentNewEventBinding.inflate(inflater, container, false) }

    private val viewModel: NewEventViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newEventState.collect {
                    if (it is LoadState.Success) findNavController().popBackStack()
                }
            }
        }

        val const =
            CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()
        val datePicker =
            MaterialDatePicker.Builder.datePicker().setCalendarConstraints(const).build()
        datePicker.addOnPositiveButtonClickListener {
            val date = LocalDateTime.ofEpochSecond(it / 1000, 0, ZoneOffset.UTC)
            binding.dateEditText.setText(date.format(DateTimeFormatter.ISO_DATE))
        }

        val timePicker = MaterialTimePicker.Builder().build()
        timePicker.addOnPositiveButtonClickListener {
            val localTime = LocalTime.of(timePicker.hour, timePicker.minute)
            binding.timeEditText.setText(localTime.toString())
        }

        binding.dateEditText.setOnFocusChangeListener { v, isFOcused ->
            if (isFOcused) datePicker.show(parentFragmentManager, "datePicker")
        }

        binding.timeEditText.setOnFocusChangeListener { v, isFOcused ->
            if (isFOcused) timePicker.show(parentFragmentManager, "timePicker")
        }

        binding.newEventAppBar.setOnMenuItemClickListener { menuIteem ->
            val startDate = binding.dateEditText.text.toString()
            val startTime = binding.timeEditText.text.toString()
            val start = "$startDate $startTime:00"
            val createRequest = EventCreateRequest(
                content = binding.eventEditText.text.toString(),
                datetime = start,
            )
            viewModel.save(createRequest)
            true
        }
    }

}