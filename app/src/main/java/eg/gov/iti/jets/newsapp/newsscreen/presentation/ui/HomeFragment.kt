package eg.gov.iti.jets.newsapp.newsscreen.presentation.ui

import android.annotation.SuppressLint
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.databinding.FragmentHomeBinding
import eg.gov.iti.jets.newsapp.newsscreen.data.local.ArticleLocalSource
import eg.gov.iti.jets.newsapp.newsscreen.data.model.NewsResultState
import eg.gov.iti.jets.newsapp.newsscreen.data.remote.ArticleRemoteSource
import eg.gov.iti.jets.newsapp.newsscreen.data.repo.RepoImpl
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel.HomeViewModel
import eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext


class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    lateinit var searchAdapter: SimpleCursorAdapter
    private  val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
    private val to = intArrayOf(R.id.searchItemID)
    private var binding: FragmentHomeBinding? = null
    private lateinit var articleAdapter: ArticleAdapter
    private var articlesList: List<Article> = ArrayList()

    private val viewModel: HomeViewModel by lazy {

        val factory = HomeViewModelFactory(
            RepoImpl.getInstance(ArticleRemoteSource(), ArticleLocalSource())!!
        )

        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpArticleRecyclerView()
        observeNewsData()
        viewModel.getNews()
        prepareSearchView()
    }
private fun prepareSearchView(){
    searchAdapter = SimpleCursorAdapter(context,
        R.layout.suggetionlayout,
        null,
        from,
        to,
        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)
    binding?.searchView?.suggestionsAdapter = searchAdapter
    binding?.searchView?.setOnQueryTextListener(
        object: android.widget.SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.searchArticles(query)
            return  true
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.search(query =newText)
            viewModel.searchArticles(newText)
            return  true
        }
    })
    binding?.searchView?.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
        override fun onSuggestionSelect(position: Int): Boolean {
            return false
        }

        @SuppressLint("Range")
        override fun onSuggestionClick(position: Int): Boolean {
            val cursor = binding?.searchView?.suggestionsAdapter?.getItem(position) as Cursor
            val selection =
                cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
            viewModel.searchArticles(selection)
            return  true
        }
    })
    lifecycleScope.launch {
        viewModel.articleSearchState.collect{
            articlesList = it
            articleAdapter.submitList(articlesList)
        }
    }
    lifecycleScope.launch{
        viewModel.stateAutoComplete.collect{
             val cursor = MatrixCursor(
                    arrayOf(
                        BaseColumns._ID,
                        SearchManager.SUGGEST_COLUMN_TEXT_1
                    )
                )
                it.forEachIndexed { index, s ->
                        cursor.addRow(arrayOf(index, s))
                }
                searchAdapter.changeCursor(cursor)
        }
    }
}
    private fun setUpArticleRecyclerView() {
        articleAdapter = ArticleAdapter()
        articlesList = listOf()
        binding?.articlesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter

        }
    }

    private fun observeNewsData() {
        lifecycleScope.launch {
            viewModel.newsState.collect {
                when (it) {
                    is NewsResultState.Loading -> {
                        binding?.articlesRecyclerView?.visibility = View.GONE
                        binding?.shimmerViewContainer?.visibility = View.VISIBLE
                        binding?.shimmerViewContainer?.startShimmer()
                    }
                    is NewsResultState.Success -> {
                        binding?.articlesRecyclerView?.visibility = View.VISIBLE
                        binding?.shimmerViewContainer?.visibility = View.GONE
                        articlesList = it.articleList
                        articleAdapter.submitList(articlesList)
                    }
                    else -> {
                        binding?.shimmerViewContainer?.visibility = View.VISIBLE
                        binding?.articlesRecyclerView?.visibility = View.GONE
                        Log.i(TAG, "getNewsDataFromApi: $it")
                    }
                }
            }
        }
    }
}