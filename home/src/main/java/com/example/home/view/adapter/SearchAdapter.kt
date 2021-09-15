package com.example.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.entities.SearchResults
import com.example.home.R
import kotlinx.android.synthetic.main.movie.view.*

class SearchAdapter(private val searchResultsList: MutableList<SearchResults.SearchItem>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded = false
    internal lateinit var context: Context

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(searchItem: SearchResults.SearchItem) {
            itemView.title.text = searchItem.title
            itemView.year.text = searchItem.year
            Glide.with(context).load(searchItem.poster)
                .into(itemView.poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView: View? = null
        when (viewType) {
            ITEM -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie, parent, false)
            LOADING -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_progress, parent, false)
        }
        context = parent.context
        return MyViewHolder(itemView!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val searchResults = searchResultsList[position]
        when (getItemViewType(position)) {
            ITEM -> holder.bind(searchResults)
        }
    }

    override fun getItemCount(): Int {
        return searchResultsList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == searchResultsList.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    fun add(r: SearchResults.SearchItem) {
        searchResultsList.add(r)
        notifyItemInserted(searchResultsList.size - 1)
    }

    fun addAll(moveResults: List<SearchResults.SearchItem>) {
        for (result in moveResults) {
            add(result)
        }
    }

    fun replaceAll(moveResults: List<SearchResults.SearchItem>) {
        searchResultsList.removeAll { true }
        addAll(moveResults)
    }

    fun remove(r: SearchResults.SearchItem?) {
        val position = searchResultsList.indexOf(r)
        if (position > -1) {
            searchResultsList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }


    fun addLoadingFooter() {
        isLoadingAdded = true
//        add(SearchResults.SearchItem())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = searchResultsList.size - 1
        val result = getItem(position)

        if (result != null) {
            searchResultsList.removeAt(position)
            notifyItemRemoved(position)
        }
    }


    fun getItem(position: Int): SearchResults.SearchItem? {
        return searchResultsList[position]
    }
}