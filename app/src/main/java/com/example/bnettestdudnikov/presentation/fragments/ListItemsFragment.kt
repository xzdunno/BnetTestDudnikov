package com.example.bnettestdudnikov.presentation.fragments

import android.app.LauncherActivity.ListItem
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnettestdudnikov.R
import com.example.bnettestdudnikov.databinding.FragmentListItemsBinding
import com.example.bnettestdudnikov.presentation.adapters.AllItemsAdapter
import com.example.bnettestdudnikov.presentation.viewmodels.ListItemsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ListItemsFragment : Fragment() {
    private var _binding: FragmentListItemsBinding? = null
    private val bind get() = _binding!!
    lateinit var allItemsAdapter: AllItemsAdapter

    companion object {
        fun newInstance() = ListItemsFragment()
    }

    private val viewModel: ListItemsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListItemsBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.isConnected.collectLatest {
                if (it) {
                    viewModel.getData()
                    bind.searchButton.visibility = View.VISIBLE
                } else {
                    bind.searchButton.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Отсутствует интернет соединение.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        viewModel.getAllData().observe(requireActivity(), Observer {
            allItemsAdapter.setListdata(it)
            allItemsAdapter.notifyDataSetChanged()
        })
        viewModel.searchData.observe(requireActivity(), Observer {
            if (it.isNotEmpty()) {
                allItemsAdapter.setListdata(it)
                allItemsAdapter.notifyDataSetChanged()
            }
        })
        viewModel.listData.observe(requireActivity(), Observer {
            viewModel.insertAll(it)
        })
        val allItemsRecView = bind.allItemsRecView
        allItemsRecView.apply {
            layoutManager = GridLayoutManager(
                context,
                2
            )
            allItemsAdapter = AllItemsAdapter()
            adapter = allItemsAdapter
        }
        bind.searchButton.setOnClickListener {

            bind.searchView.visibility = View.VISIBLE
            bind.closeButton.visibility = View.VISIBLE
            if (bind.searchView.text.isNotEmpty())
                viewModel.search(bind.searchView.text.toString())
        }
        bind.closeButton.setOnClickListener {
            bind.searchView.text.clear()
            bind.searchView.visibility = View.GONE
            bind.closeButton.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}