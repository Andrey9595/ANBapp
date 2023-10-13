package ru.anb.myapplication.features.home.ui.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.anb.myapplication.features.home.domain.job.JobRepository
import ru.anb.myapplication.features.home.domain.model.job.JobCreateRequest
import javax.inject.Inject

@HiltViewModel
class NewJobViewModel @Inject constructor(private val repository: JobRepository) : ViewModel() {

    fun create(jobCreateRequest: JobCreateRequest) {
        viewModelScope.launch {
            repository.addJob(jobCreateRequest)
        }
    }
}