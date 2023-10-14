package ru.anb.myapplication.features.home.db.job

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.anb.myapplication.features.home.domain.model.job.JobCreateRequest
import ru.anb.myapplication.features.home.domain.model.job.JobResponse

interface JobsApi {

    @GET("/api/{user_id}/jobs/")
    suspend fun getJobsById(@Path("user_id") id: Long): Response<List<JobResponse>>

    @POST("/api/my/jobs/")
    suspend fun createJob(@Body jobCreateRequest: JobCreateRequest): Response<JobResponse>

    @DELETE("/api/my/jobs/{job_id}/")
    suspend fun removeById(@Path("job_id") id: Long): Response<Unit>
}