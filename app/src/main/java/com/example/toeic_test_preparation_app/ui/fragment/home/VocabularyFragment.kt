package com.example.toeic_test_preparation_app.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toeic_test_preparation_app.ui.adapter.VocabularyTopicAdapter
import com.example.toeic_test_preparation_app.ui.viewmodel.VocabularyTopicViewModel
import com.example.toeic_test_preparation_app.R

class VocabularyFragment : Fragment() {

    private lateinit var viewModel: VocabularyTopicViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VocabularyTopicAdapter
    private lateinit var emptyView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_vocabulary, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.listTopic)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = VocabularyTopicAdapter(emptyList())
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[VocabularyTopicViewModel::class.java]
        emptyView = view.findViewById<View>(R.id.emptyView)

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.topics.observe(viewLifecycleOwner) { result ->
            result.onSuccess { topics ->
                if (topics.isEmpty()) {
//                    recyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                } else {
                    adapter.updateTopicList(topics)
                    recyclerView.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
                }
            }.onFailure {
//                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            }
        }

        if (viewModel.topics.value?.getOrNull().isNullOrEmpty()) {
            viewModel.loadTopicsIfNeeded()
        }
    }
}