package com.example.navigationsafeargs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationsafeargs.R
import com.example.navigationsafeargs.databinding.FragmentListOfDeletedNoteBinding
import com.example.navigationsafeargs.ui.adapters.DeletedTaskAdapter
import com.example.navigationsafeargs.viewmodels.NoteViewModel
import kotlinx.coroutines.launch


class ListOfDeletedNoteFragment : BaseFragment<FragmentListOfDeletedNoteBinding,NoteViewModel>() {

    private lateinit var deletedTaskAdapter: DeletedTaskAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListOfDeletedNoteBinding {
        return FragmentListOfDeletedNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setupToolbar()
    }

    private fun setupRecyclerView() {
        deletedTaskAdapter = DeletedTaskAdapter(mutableListOf())
        binding.recycleview1.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = deletedTaskAdapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.deletedTasks.collect { deletedTasks ->
                deletedTaskAdapter.updateItems(deletedTasks)
            }
        }
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            lifecycleScope.launch {
                findNavController().navigate(R.id.action_deletedNoteFragment_to_home_fragment)
            }
        }
    }
}


