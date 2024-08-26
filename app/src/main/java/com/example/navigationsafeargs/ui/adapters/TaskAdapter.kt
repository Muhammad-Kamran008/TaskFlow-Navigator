package com.example.navigationsafeargs.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationsafeargs.data.model.TaskItem
import com.example.navigationsafeargs.databinding.TaskItemBinding
import com.example.navigationsafeargs.ui.fragments.ListOfNoteFragmentDirections


class TaskAdapter(
    private val items: MutableList<TaskItem>,
    private val onDeleteClick: (TaskItem) -> Unit

) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskItem = items[position]
        holder.binding.titel.text = items[position].taskname
        holder.binding.descrepthion.text = items[position].taskDescription
        holder.binding.delete.setOnClickListener {
            onDeleteClick(taskItem)
        }

        holder.binding.root.setOnClickListener {
            val action =
                ListOfNoteFragmentDirections.actionMobileNavigationToAddNoteFragment(items[position])
            findNavController(it).navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    private fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<TaskItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun deleteItem(taskItem: TaskItem) {
        val position = items.indexOf(taskItem)
        if (position != -1) {
            removeItem(position)
        }
    }

}
