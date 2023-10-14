package ru.anb.myapplication.features.home.db.job

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.anb.myapplication.R
import ru.anb.myapplication.core.domain.LoadState
import ru.anb.myapplication.features.home.domain.job.JobRepository
import ru.anb.myapplication.features.home.domain.model.job.JobCreateRequest
import ru.anb.myapplication.features.home.domain.model.job.JobModel
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val jobsApi: JobsApi,
    private val jobsDao: JobsDao
) : JobRepository {
    override fun jobsData(): Flow<List<JobModel>> {
        return jobsDao.getAll().map { list -> list.map { entity -> entity.toModel() } }
    }

    override suspend fun getAllJobs(id: Long) {
        val response = jobsApi.getJobsById(id).body()
        if (response != null)
            jobsDao.insert(response.map { it.toEntity() })
    }

    override suspend fun addJob(jobRequest: JobCreateRequest): LoadState<Unit> {
        val jobResponse = jobsApi.createJob(jobRequest).body()
        return if (jobResponse != null) {
            jobsDao.insert(jobResponse.toEntity())
            LoadState.Success(Unit)
        } else LoadState.Error(R.string.name_error)
    }

    override suspend fun removeJob(id: Long) {
        val result = jobsApi.removeById(id)
        if (result.isSuccessful)
            jobsDao.removeById(id)
    }
}