package ru.anb.myapplication.features.home.domain.model.job

import ru.anb.myapplication.features.home.db.job.JobEntity

data class JobResponse(
    val id: Long,
    var userId: Long,
    val name: String,
    val position: String,
    val start: String,
    val finish: String?,
    val link: String?
){

    fun toEntity(): JobEntity{
        return JobEntity(userId, id,false, name, position, start, finish, link )
    }
}
