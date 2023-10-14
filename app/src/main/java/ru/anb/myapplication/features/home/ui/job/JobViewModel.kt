package ru.anb.myapplication.features.home.ui.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.anb.myapplication.features.home.domain.job.JobRepository
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(private val jobRepository: JobRepository) : ViewModel() {

    val jobFlow = jobRepository.jobsData()

    fun refresh(id: Long) {
        viewModelScope.launch { jobRepository.getAllJobs(id) }
    }

    fun remove(id: Long) {
        viewModelScope.launch {
            jobRepository.removeJob(id)
        }
    }
}