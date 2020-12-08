package com.example.jotto_game.game.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jotto_game.R
import com.example.jotto_game.game.data.ExampleItem
import kotlinx.android.synthetic.main.example_item.view.*

class WordAdapter(private val wordList: List<ExampleItem>) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordView: TextView = itemView.text_view
        val numberView: TextView = itemView.number_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent, false
        )

        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentItem = wordList[position]
        holder.wordView.text = currentItem.word
        holder.numberView.text = currentItem.number

    }

    override fun getItemCount() = wordList.size
}