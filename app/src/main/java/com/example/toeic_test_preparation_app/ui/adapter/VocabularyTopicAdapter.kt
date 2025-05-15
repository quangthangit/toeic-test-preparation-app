package com.example.toeic_test_preparation_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toeic_test_preparation_app.R
import com.example.toeic_test_preparation_app.data.remote.VocabularyTopicResponse

class VocabularyTopicAdapter(
    private var topics: List<VocabularyTopicResponse>
) : RecyclerView.Adapter<VocabularyTopicAdapter.TopicViewHolder>() {

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topicNameTextView: TextView = itemView.findViewById(R.id.name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.des)
        val totalWordsTextView: TextView = itemView.findViewById(R.id.totalWord)
        val totalLessonsTextView: TextView = itemView.findViewById(R.id.totalLesson)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_topic, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topics[position]
        holder.topicNameTextView.text = topic.name
        holder.descriptionTextView.text = topic.des
        holder.totalWordsTextView.text = " • ${topic.totalWord} Words"
        holder.totalLessonsTextView.text = " • ${topic.totalLesson} Lessons"

        holder.itemView.setOnClickListener {
            // TODO: Handle item click if needed
        }
    }

    override fun getItemCount(): Int = topics.size

    fun updateTopicList(newTopicList: List<VocabularyTopicResponse>) {
        topics = newTopicList
        notifyDataSetChanged()
    }
}
