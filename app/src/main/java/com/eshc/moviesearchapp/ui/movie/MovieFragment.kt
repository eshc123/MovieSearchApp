package com.eshc.moviesearchapp.ui.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.moviesearchapp.R
import com.eshc.moviesearchapp.databinding.FragmentMovieBinding
import com.eshc.moviesearchapp.ui.adapter.MovieAdapter
import com.eshc.moviesearchapp.ui.util.addPagingListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private val viewModel : MovieViewModel by viewModels()

    private val movieAdapter : MovieAdapter by lazy {
        MovieAdapter(
            movieClickListener = {
                startActionView(it)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initView(){
        binding?.let {
            it.viewModel = viewModel
            initRecyclerView(it.rvMovie)
            initSetOnClickListener(it)
        }
    }

    private fun initSetOnClickListener(binding: FragmentMovieBinding){
        binding.btnRecent.setOnClickListener {
            moveToRecentFragment()
        }
        binding.btnSearch.setOnClickListener {
            movieAdapter.submitList(emptyList())
            viewModel.setMovies()
        }
    }

    private fun initRecyclerView(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = movieAdapter
        recyclerView.addPagingListener()
    }

    private fun initObserver() {
        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }

    private fun startActionView(link : String){
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(link)
            )
        )
    }

    private fun moveToRecentFragment() {
        findNavController().navigate(
            R.id.action_movieFragment_to_recentFragment
        )
    }

    private fun RecyclerView.addPagingListener() {
        addOnScrollListener( object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount ?: 0
                if (lastVisibleItemPosition + 5 >= itemTotalCount  && !viewModel.loading) {
                    viewModel.addMovies()
                }
            }
        })
    }
}

