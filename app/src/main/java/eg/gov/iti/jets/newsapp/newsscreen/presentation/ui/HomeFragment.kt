package eg.gov.iti.jets.newsapp.newsscreen.presentation.ui

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
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
                        binding.articlesRecyclerView.visibility = View.GONE
                        binding.shimmerViewContainer.visibility = View.VISIBLE
                        binding.shimmerViewContainer.startShimmer()
                    }
                    is NewsResultState.Success -> {
                        binding.articlesRecyclerView.visibility = View.VISIBLE
                        binding.shimmerViewContainer.visibility = View.GONE
                        articlesList = it.articleList
                        articleAdapter.submitList(articlesList)
                    }
                    else -> {
                        binding.shimmerViewContainer.visibility = View.VISIBLE
                        binding.articlesRecyclerView.visibility = View.GONE
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