package ru.anb.myapplication.features.home.db.job

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anb.myapplication.features.home.domain.model.job.JobModel

@Entity
data class JobEntity(
    val userId: Long,
    @PrimaryKey
    val id: Long,
    val ownedByMe: Boolean,
    val name: String,
    val position: String,
    val start: String,
    val finish: String?,
    val link: String?
) {
    fun toModel(): JobModel {
        return JobModel(userId, id, ownedByMe, name, position, start, finish, link)
    }
}
