package com.example.navigationsafeargs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationsafeargs.R
import com.example.navigationsafeargs.data.interfaces.OnItemDeleteListener
import com.example.navigationsafeargs.data.model.TaskItem
import com.example.navigationsafeargs.databinding.FragmentListofNoteBinding
import com.example.navigationsafeargs.helpers.toast
import com.example.navigationsafeargs.ui.adapters.TaskAdapter
import com.example.navigationsafeargs.ui.components.MyDialog
import com.example.navigationsafeargs.viewmodels.NoteViewModel
import kotlinx.coroutines.launch

class ListOfNoteFragment : BaseFragment<FragmentListofNoteBinding,NoteViewModel>(),OnItemDeleteListener {

    private lateinit var taskAdapter: TaskAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListofNoteBinding {
        return FragmentListofNoteBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.febAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mobile_navigation_to_addNoteFragment)
        }
    }

    private fun setupRecyclerView() {

        taskAdapter = TaskAdapter(mutableListOf()) { taskItem ->
            showDeleteConfirmationDialog(taskItem)
        }

        binding.recycleview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }
    }

    private fun observeViewModel() {
//        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
//            taskAdapter.updateItems(tasks)
//        }

        lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                taskAdapter.updateItems(tasks)
            }
        }
    }


    private fun showDeleteConfirmationDialog(taskItem: TaskItem) {
        val confirmDeleteDialog = MyDialog(
            requireContext(),
            onConfirmDelete = {
                viewModel.deleteTask(taskItem)
                taskAdapter.deleteItem(taskItem)
                requireContext().toast("Data Deleted Successfully")
                lifecycleScope.launch {
                    findNavController().navigate(R.id.action_mobile_navigation_to_ListOfDeletedNoteFragment)
                }
            },
            onCancel = {


            }
        )
        confirmDeleteDialog.show()
    }

    override fun onDeleteConfirmed(taskItem: TaskItem) {
        viewModel.deleteTask(taskItem)
        requireContext().toast("Data Deleted Successfully")
    }
}

