package com.example.movies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.data.server.BASE_URL_IMG
import com.example.movies.ui.MainActivity
import com.example.movies.ui.common.app
import com.example.movies.ui.common.toHttpsUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var detailState: DetailState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailState = DetailState(requireContext())

        binding.switchWatched.setOnClickListener { viewModel.onWatchedSwitched() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect(::updateUi) //{updateUi(it)}
            }
        }

        viewModel.onUiReady()

    }


    private fun updateUi(state: DetailViewModel.UiState) {
        state.movie?.let { movie ->
            (activity as MainActivity)
                .supportActionBar?.title = movie.name

            with(binding) {
                overviewText.text = movie.overview
                userScoreBar.rating = (movie.userScore / 2)
                switchWatched.isChecked = movie.isWatched

                val trailer = movie.trailer
                trailerButton.isEnabled = trailer.isNotBlank()
                trailerButton.setOnClickListener {
                    detailState.onTrailerClicked(it, trailer)
                }

                val url = (BASE_URL_IMG + movie.image).toHttpsUrl()
                imagePoster.load(url)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}