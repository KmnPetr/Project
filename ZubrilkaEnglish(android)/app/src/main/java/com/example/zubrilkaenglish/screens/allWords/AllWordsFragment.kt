package com.example.zubrilkaenglish.screens.allWords

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.example.zubrilkaenglish.databinding.FragmentAllWordsBinding
import com.example.zubrilkaenglish.screens.activity.MainViewModel
import com.example.zubrilkaenglish.utils.APP

class AllWordsFragment : Fragment() {

    private lateinit var viewModel: AllWordsViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentAllWordsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllWordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentAllWordsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        APP = requireContext()

        viewModel = ViewModelProvider(this).get(AllWordsViewModel::class.java)
        mainViewModel= ViewModelProvider(this).get(MainViewModel::class.java)

        recyclerView=binding.rvAllWords
        adapter=AllWordsAdapter()
        recyclerView.adapter=adapter


        mainViewModel.getListAllWords().observe(viewLifecycleOwner){list->
            adapter.setList(list)
        }

    }
}