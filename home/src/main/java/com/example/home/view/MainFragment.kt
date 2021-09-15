package com.example.home.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.di.component.BaseComponent
import com.example.base.di.component.BaseNavigationComponent
import com.example.base.presentation.BaseActivity
import com.example.base.presentation.TabFragment
import com.example.base.subscriber.ResultState
import com.example.base.util.NetworkConnection
import com.example.base.util.PaginationScrollListener
import com.example.domain.entities.SearchResults
import com.example.home.R
import com.example.home.databinding.FragmentMainFramentBinding
import com.example.home.di.DaggerHomePageComponent
import com.example.home.view.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_main_frament.*
import kotlinx.android.synthetic.main.view_error.*
import java.util.*
import javax.inject.Inject

class MainFragment : TabFragment<SearchViewModel>() {

    lateinit var searchView: SearchView
    lateinit var mAdapter: SearchAdapter
    var TOTAL_PAGES = 20
    var PAGE_START = 1
    var isLoading = false
    var isLastPage = false
    var currentPage = PAGE_START
    lateinit var linearLayoutManager: LinearLayoutManager
    internal var searchResultsList: MutableList<SearchResults.SearchItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainFramentBinding.inflate(inflater, container, false)
        checkConnection()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        tabLayout = binding.root.findViewById(R.id.tab)
        pager = binding.root.findViewById(R.id.pager)
        setHasOptionsMenu(true)
        return binding.root
    }

    @Inject
    override lateinit var viewModel: SearchViewModel

    override fun injectComponent() {
        DaggerHomePageComponent.builder()
            .baseComponent(getBaseComponent(BaseComponent::class.java))
            .baseNavigationComponent(getAppSubComponent(BaseNavigationComponent::class.java))
            .build()
            .inject(this)
    }

    override fun onInitViews() {
        super.onInitViews()
        val supportActionBar = (activity as BaseActivity<*>).supportActionBar

        supportActionBar?.title = getString(R.string.app_name)

    }

    override fun onInitObservers() {
        super.onInitObservers()
        showLoading()
        movie_recycler_view.showShimmerAdapter()
        viewModel.searchData.observe(this, Observer {
            onResultLoaded(it)
        })
    }

    private fun onResultLoaded(resultState: ResultState<SearchResults>?) {
        hideLoading()
        when (resultState) {
            is ResultState.Success -> {
                onGetSearch(resultState.data)
            }
            is ResultState.Error -> {
                showError(resultState.throwable)
            }
        }
    }

    private fun onGetSearch(search: SearchResults) {
        search.search?.forEach {
            titleSearch.text = it.title
        }
        searchResultsList.addAll(search.search!!)
        initSearchAdapter()
        addScrollPagination()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)
        searchView = menu.findItem(R.id.searchView).actionView as SearchView
        searchView.queryHint = "Search"
        searchView.isSubmitButtonEnabled = true
        searchView.onActionViewExpanded()
        search(searchView)

    }

    private fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getSearchResultMoviesData()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    fun addScrollPagination() {
        movie_recycler_view.addOnScrollListener(object : PaginationScrollListener(
            linearLayoutManager
        ) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
            }

            override fun getTotalPageCount(): Int {
                return TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    fun getSearchResultMoviesData() {
        movie_recycler_view.visibility = View.VISIBLE
        linear_layout.visibility = View.GONE
        movie_recycler_view.showShimmerAdapter()
        onInitObservers()
        searchView.onActionViewCollapsed()
    }

    fun initSearchAdapter() {
        mAdapter = SearchAdapter(searchResultsList)
        movie_recycler_view.adapter = mAdapter
        linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        movie_recycler_view.layoutManager = linearLayoutManager
    }

    private fun checkConnection() {
        val networkConnection = NetworkConnection(requireContext())
       networkConnection.observe(viewLifecycleOwner, Observer { isConnected ->
           if (isConnected) {
               linear_layout.visibility = View.VISIBLE
           } else {
               showToast("Lost Connection, Please turn on your Wifi")
               linear_layout.visibility = View.GONE
           }
       })
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(),text, Toast.LENGTH_LONG).show()
    }



    private fun showError(throwable: Throwable) {
//        errorView.visibility = View.VISIBLE
        tvErrorMessage.text = throwable.localizedMessage
    }

    private fun hideError() {
//        errorView.visibility = View.GONE
    }

    private fun showLoading() {
//        progressBarView.visibility = View.VISIBLE
    }

    private fun hideLoading() {
//        progressBarView.visibility = View.GONE
    }



}