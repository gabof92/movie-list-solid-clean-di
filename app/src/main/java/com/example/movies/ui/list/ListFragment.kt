package com.example.movies.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.databinding.FragmentListBinding
import com.example.movies.ui.common.app
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ListViewModelFactory

    private val viewModel: ListViewModel by viewModels { vmFactory }

    private lateinit var listState: ListState

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*This will go through all the lines tagged @Inject within ListFragment
        and inject the corresponding dependencies*/
        app.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listState = ListState(findNavController())

        val recyclerView = binding.list
        recyclerView.adapter = MovieListAdapter { listState.navigateTo(it) }
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect(::updateUi) //{updateUi(it)}
            }
        }

        viewModel.onUiReady()

    }

    private fun updateUi(state: ListViewModel.UiState) {
        state.movies?.let {
            val adapter = binding.list.adapter as MovieListAdapter
            adapter.submitList(it)
        }
        //Handle error Ui display
        var errorText = ""
        state.error?.let {
            errorText = when (it) {
                com.example.domain.Error.Connectivity -> getString(R.string.connectivity_error)
                is com.example.domain.Error.Server -> getString(R.string.server_error) + it.code
                is com.example.domain.Error.Unknown -> getString(R.string.unknown_error) + it.message
            }
        }
        binding.error.text = errorText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}