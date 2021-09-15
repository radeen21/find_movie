package com.example.home.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainFragment : TabFragment<SearchViewModel>() {

    lateinit var searchView: SearchView
    lateinit var mAdapter: SearchAdapter
    var isLoading = false
    var isLastPage = false
    var currentPage = PAGE_START
    lateinit var linearLayoutManager: LinearLayoutManager
    internal var searchResultsList: MutableList<SearchResults.SearchItem> = ArrayList()

    companion object {
        const val TOTAL_PAGES = 20
        const val PAGE_START = 1
    }

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
        initSearchAdapter()
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.searchData.observe(this, Observer {
            onResultLoaded(it)
        })
    }

    private fun onResultLoaded(resultState: ResultState<SearchResults>?) {
        when (resultState) {
            is ResultState.Loading -> {
                if (currentPage > 1) {
                    progress_bar.visibility = View.VISIBLE
                }
            }
            is ResultState.Success -> {
                onGetSearch(resultState.data)
                isLoading = false
                if (currentPage > 1) {
                    progress_bar.visibility = View.GONE
                }
            }
            is ResultState.Error -> {
                isLoading = false
                if (currentPage > 1) {
                    progress_bar.visibility = View.GONE
                }
            }
        }
    }

    private fun onGetSearch(search: SearchResults) {
        if (currentPage == PAGE_START) movie_recycler_view.hideShimmerAdapter()
        search.search?.let {
            if (currentPage == PAGE_START) mAdapter.replaceAll(it.toMutableList())
            else mAdapter.addAll(it)
        }
        if (null == search.search) Log.d("empty", "empty search")
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
        getSearchResultMoviesData()
    }

    private fun search(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                movie_recycler_view.showShimmerAdapter()
                currentPage = PAGE_START
                GlobalScope.launch { viewModel.fetchSearch(query, currentPage) }
                hideKeyboard()
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
                viewModel.search(searchView.query.toString(), currentPage)
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
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard(){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }


}