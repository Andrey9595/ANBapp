package ru.anb.myapplication.features.home.domain.job

import kotlinx.coroutines.flow.Flow
import ru.anb.myapplication.core.domain.AppLoadState
import ru.anb.myapplication.features.home.domain.model.job.JobCreateRequest
import ru.anb.myapplication.features.home.domain.model.job.JobModel

interface JobRepository {
    fun jobsData(): Flow<List<JobModel>>

    suspend fun getAllJobs(id: Long)

    suspend fun addJob(jobRequest: JobCreateRequest): AppLoadState<Unit>

    suspend fun removeJob(id: Long)


}