package ru.anb.myapplication.features.home.ui.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.home.domain.job.JobRepository
import ru.anb.myapplication.features.home.domain.model.job.JobCreateRequest
import javax.inject.Inject

@HiltViewModel
class NewJobViewModel @Inject constructor(private val repository: JobRepository) : ViewModel() {

    private val _jobCreateState = MutableStateFlow<LoadState<Unit>>(LoadState.NotLoadedYet())

    val jobCreateState get() = _jobCreateState.asStateFlow()

    fun create(jobCreateRequest: JobCreateRequest) {
        _jobCreateState.value = LoadState.Loading()
        viewModelScope.launch {
            val result = repository.addJob(jobCreateRequest)
            _jobCreateState.value = result
        }
    }
}