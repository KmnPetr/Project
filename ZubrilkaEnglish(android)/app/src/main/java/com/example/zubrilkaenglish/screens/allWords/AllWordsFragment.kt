package com.example.zubrilkaenglish.screens.allWords

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zubrilkaenglish.databinding.FragmentAllWordsBinding

class AllWordsFragment : Fragment() {

    private lateinit var viewModel: AllWordsViewModel
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

        viewModel = ViewModelProvider(this).get(AllWordsViewModel::class.java)

        recyclerView=binding.rvAllWords
        adapter=AllWordsAdapter()
        recyclerView.adapter=adapter

        viewModel.requestListWordsFromInternet()
        Toast.makeText(requireContext(),"пришли данные с инета", Toast.LENGTH_LONG).show()

        viewModel.listAllWords.observe(viewLifecycleOwner){list->
            adapter.setList(list)
        }

    }
}