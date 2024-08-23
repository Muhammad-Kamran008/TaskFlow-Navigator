package com.example.navigationsafeargs.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationsafeargs.R
import android.text.TextUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationsafeargs.data.model.TaskItem
import com.example.navigationsafeargs.databinding.FragmentAddNoteBinding
import com.example.navigationsafeargs.helpers.toast
import com.example.navigationsafeargs.viewmodels.NoteViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : BaseFragment() {
    private var utaskItem: TaskItem? = null
    private lateinit var addNoteBinding: FragmentAddNoteBinding
    private val binding get() = addNoteBinding

    private val viewModel: NoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        addNoteBinding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        arguments?.let {
            utaskItem = AddNoteFragmentArgs.fromBundle(it).taskItem
            binding.etName.setText(utaskItem?.taskname ?: "")
            binding.etDescription.setText(utaskItem?.taskDescription ?: "")
        }


        binding.btnAdd.setOnClickListener {
            if (validation()) {
                GlobalScope.launch {


                    context?.let {
                        val taskItem = TaskItem(
                            binding.etName.text.toString(),
                            binding.etDescription.text.toString(),
                            0
                        );
                        if (utaskItem == null) {
                            viewModel.insertTask(taskItem)

                        } else {
                            taskItem.id = utaskItem?.id!!
                            viewModel.updateTask(taskItem)
                        }

                    }

                }
                if (utaskItem == null) {
                    context?.toast("Data Insert Successfully")
                } else {
                    context?.toast("Data Updated Successfully")
                }
                findNavController().navigate(R.id.action_addNoteFragment_to_home_fragment)

            }
        }
    }


    private fun validation(): Boolean {
        if (TextUtils.isEmpty(binding.etName.text)) {
            context?.toast("Please enter task title")
            return false
        } else if (TextUtils.isEmpty(binding.etDescription.text)) {
            context?.toast("Please enter task description")
            return false
        }
        return true
    }
}

