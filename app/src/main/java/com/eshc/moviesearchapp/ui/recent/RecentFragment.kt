package com.eshc.moviesearchapp.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eshc.moviesearchapp.databinding.FragmentRecentBinding
import com.eshc.moviesearchapp.ui.adapter.RecentAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

class RecentFragment : Fragment() {
    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding

    private val recentAdapter: RecentAdapter by lazy {
        RecentAdapter(
            recentClickListener = {
                backToMovieFragment()
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecentBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    private fun initView() {
        binding?.let {
            initRecyclerView(it.rvRecent)

        }
    }


    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
        }
        recyclerView.adapter = recentAdapter
    }

    private fun initObserver() {

    }

    private fun backToMovieFragment() {
        findNavController().navigateUp()
    }
}