package com.example.navigationsafeargs.ui.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationsafeargs.data.model.DeletedTaskItem
import com.example.navigationsafeargs.databinding.DeletedTaskItemBinding


class DeletedTaskAdapter(
    private val items: MutableList<DeletedTaskItem>
) : RecyclerView.Adapter<DeletedTaskAdapter.DeletedTaskViewHolder>() {

    class DeletedTaskViewHolder(val binding: DeletedTaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeletedTaskViewHolder {
        return DeletedTaskViewHolder(
            DeletedTaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DeletedTaskViewHolder, position: Int) {
        holder.binding.title1.text = items[position].taskname
        holder.binding.description1.text = items[position].taskDescription

    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<DeletedTaskItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}


