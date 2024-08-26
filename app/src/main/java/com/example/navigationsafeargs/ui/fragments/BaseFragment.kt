package com.example.navigationsafeargs.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.navigationsafeargs.viewmodels.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel



abstract class BaseFragment<VBinding : ViewBinding, VModel : ViewModel> : Fragment() {

    val viewModel: NoteViewModel by viewModel()
    protected lateinit var binding: VBinding
    protected abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}


