package com.trotalab.moviesmvvm.feature.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.trotalab.moviesmvvm.R
import com.trotalab.moviesmvvm.feature.movies.viewmodel.MoviesViewModel
import com.vn.fsoft.gstlib.core.extensions.gone
import com.vn.fsoft.gstlib.core.extensions.observe
import com.vn.fsoft.gstlib.core.extensions.toast
import com.vn.fsoft.gstlib.core.extensions.visible
import com.vn.fsoft.gstlib.core.network.api.isError
import com.vn.fsoft.gstlib.core.network.api.isLoading
import com.vn.fsoft.gstlib.core.network.api.isSuccessful
import com.vn.fsoft.gstlib.core.platform.BaseFragment
import com.vn.fsoft.gstlib.core.platform.FragmentToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*


@AndroidEntryPoint
class MoviesFragment : BaseFragment(R.layout.fragment_movies) {

    private val viewModel by viewModels<MoviesViewModel>()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun subscribeObservers() {
        observe(viewModel.movies) { state ->
            when {
                state.status.isSuccessful() -> {
                    moviesAdapter.submitList(state.data)
                }
                state.status.isError() -> {
                    requireContext().toast {
                        state.exception?.message.toString()
                    }
                }
            }

            if (state.status.isLoading()) cardViewListLoading.visible() else cardViewListLoading.gone()
        }
    }

    override fun toolbarBuilder(): FragmentToolbar {
        return FragmentToolbar.Builder()
            .withId(R.id.toolbar)
            .withTitle(R.string.app_name)
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }

    private fun setupViews() {
        rvwMovies.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        moviesAdapter = MoviesAdapter {

        }
        rvwMovies.adapter = moviesAdapter
    }
}