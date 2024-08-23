package com.example.navigationsafeargs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationsafeargs.R
import com.example.navigationsafeargs.data.model.TaskItem
import com.example.navigationsafeargs.databinding.FragmentListofNoteBinding
import com.example.navigationsafeargs.helpers.toast
import com.example.navigationsafeargs.ui.adapters.TaskAdapter
import com.example.navigationsafeargs.viewmodels.NoteViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListOfNoteFragment : Fragment() {

    private var _binding: FragmentListofNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModel()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListofNoteBinding.inflate(inflater, container, false)
        return binding.root
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
        taskAdapter = TaskAdapter(mutableListOf(), this)
        binding.recycleview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.updateItems(tasks)
        }

//        lifecycleScope.launch {
//            viewModel.tasks.collect { tasks ->
//                taskAdapter.updateItems(tasks)
//            }
//        }
    }

    fun deleteItem(taskItem: TaskItem) {
        viewModel.deleteTask(taskItem)
        requireContext().toast("Data Deleted Successfully")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
