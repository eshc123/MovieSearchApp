package com.eshc.moviesearchapp.ui.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.moviesearchapp.databinding.FragmentMovieBinding
import com.eshc.moviesearchapp.ui.adapter.MovieAdapter

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

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
            initRecyclerView(it.rvMovie)
        }
    }


    private fun initRecyclerView(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = movieAdapter
    }

    private fun initObserver() {

    }

    private fun startActionView(link : String){
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(link)
            )
        )
    }
}