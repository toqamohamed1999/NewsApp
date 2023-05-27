package eg.gov.iti.jets.newsapp.newsscreen.presentation.ui

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eg.gov.iti.jets.newsapp.databinding.FragmentHomeBinding
import eg.gov.iti.jets.newsapp.newsscreen.data.local.ArticleLocalSource
import eg.gov.iti.jets.newsapp.newsscreen.data.model.NewsResultState
import eg.gov.iti.jets.newsapp.newsscreen.data.remote.ArticleRemoteSource
import eg.gov.iti.jets.newsapp.newsscreen.data.repo.RepoImpl
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel.HomeViewModel
import eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter
    private var articlesList: List<Article> = ArrayList()


    private val viewModel: HomeViewModel by lazy {

        val factory = HomeViewModelFactory(
            RepoImpl.getInstance(ArticleRemoteSource(), ArticleLocalSource(requireContext()))!!
        )

        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpArticleRecyclerView()
        observeNewsData()
    }

    private fun setUpArticleRecyclerView() {
        articleAdapter = ArticleAdapter()

        binding.articlesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
        }
    }

    private fun observeNewsData() {

        lifecycleScope.launch {
            viewModel.newsState.collectLatest {
                when (it) {
                    is NewsResultState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is NewsResultState.Success -> {
                        articlesList = it.articleList
                        articleAdapter.submitList(articlesList)
                        Log.i(TAG, "observeNewsData: "+it.articleList)
                        binding.progressBar.visibility = View.GONE

                        var filterTitle = viewModel.getTitleArtical(articlesList)
                        var complete = binding.autoCompleteTextView
                        var completeAdapter = ArrayAdapter<String>(
                            requireContext(),
                            R.layout.simple_dropdown_item_1line, filterTitle
                        )
                        complete.setAdapter(completeAdapter)
                        complete.setThreshold(1)

                        complete.setOnItemClickListener { parent, view, position, id ->
                           // println("filter title::: ${}")
                          // var newList = articlesList.map { articlesList.get(position) }
                          //  articleAdapter.submitList(newList)
                            binding.searchView.setQuery(filterTitle.get(position), true)
                            Toast.makeText(context,parent.getItemIdAtPosition(position).toString(),Toast.LENGTH_LONG).show()

                        }
                        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                val filterArticalList = viewModel.searchArticles(articlesList,query ?: "")
                                articleAdapter.submitList(filterArticalList)
                                return false
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                newText?.let {
                                    val filterArticalList = viewModel.searchArticles(articlesList,it)
                                    articleAdapter.submitList(filterArticalList)
                                    var filterTitle = viewModel.getTitleArtical(articlesList)
                                }
                                return true
                            }
                        })


                    }
                    else -> {
                        binding.progressBar.visibility = View.GONE
                        Log.i(TAG, "getNewsDataFromApi: $it")
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}