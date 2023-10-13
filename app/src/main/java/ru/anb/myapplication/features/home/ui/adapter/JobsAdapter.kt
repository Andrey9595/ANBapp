package ru.anb.myapplication.features.home.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.anb.myapplication.core.extensions.asString
import ru.anb.myapplication.core.extensions.toLocalDateTime
import ru.anb.myapplication.databinding.CardJobBinding
import ru.anb.myapplication.features.home.domain.model.job.JobModel

class JobsAdapter(private val onRemove: (Long) -> Unit) :
    RecyclerView.Adapter<JobsAdapter.JobsViewHolder>() {

    private var listJob = listOf<JobModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<JobModel>) {
        listJob = list
        notifyDataSetChanged()
    }

    inner class JobsViewHolder(private val binding: CardJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JobModel) {
            with(binding) {
                name.text = item.name
                jobName.text = item.position
                jobStart.text = item.start.take(19).toLocalDateTime().asString()
                jobEnd.text = item.finish?.take(19)?.toLocalDateTime()?.asString()
                linkOfJob.text = item.link
                menuBtn.setOnClickListener {
                    onRemove.invoke(item.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        return JobsViewHolder(CardJobBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listJob.size


    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val item = listJob[position]
        holder.bind(item)
    }
}