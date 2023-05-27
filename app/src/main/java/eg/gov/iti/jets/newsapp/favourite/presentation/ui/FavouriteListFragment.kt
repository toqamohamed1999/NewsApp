package eg.gov.iti.jets.newsapp.favourite.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.databinding.FragmentFavouriteListBinding
import eg.gov.iti.jets.newsapp.databinding.FragmentHomeBinding
import eg.gov.iti.jets.newsapp.databinding.FragmentLoginBinding
import eg.gov.iti.jets.newsapp.favourite.data.local.FavLocalSourceImp
import eg.gov.iti.jets.newsapp.favourite.data.repo.FavouriteRepoImp
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavResultState
import eg.gov.iti.jets.newsapp.favourite.domain.model.FavouriteArticleModel
import eg.gov.iti.jets.newsapp.favourite.presentation.viewModel.FavViewModelFactory
import eg.gov.iti.jets.newsapp.favourite.presentation.viewModel.FavouriteViewModel
import eg.gov.iti.jets.newsapp.newsscreen.data.local.ArticleLocalSource
import eg.gov.iti.jets.newsapp.newsscreen.data.model.NewsResultState
import eg.gov.iti.jets.newsapp.newsscreen.data.remote.ArticleRemoteSource
import eg.gov.iti.jets.newsapp.newsscreen.data.repo.RepoImpl
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.presentation.ui.ArticleAdapter
import eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel.HomeViewModel
import eg.gov.iti.jets.newsapp.newsscreen.presentation.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FavouriteListFragment : Fragment() {
    private  val TAG = "FavouriteListFragment"

    private var _binding: FragmentFavouriteListBinding? = null
    private val binding get() = _binding!!
    private var favArticleList: List<FavouriteArticleModel> = ArrayList()
    private lateinit var favAdapter : AdapterFavouriteList

    private val viewModel: FavouriteViewModel by lazy {

        val factory = FavViewModelFactory(
            FavouriteRepoImp(FavLocalSourceImp())!!
        )

        ViewModelProvider(this, factory)[FavouriteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpFavRecyclerView()
        observeFavData()
    }

    private fun setUpFavRecyclerView() {
        favAdapter = AdapterFavouriteList(favArticleList)

        binding.favouriteListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favAdapter
        }
    }

    private fun observeFavData() {

        lifecycleScope.launch {
            viewModel.favState.collectLatest {
                when (it) {
                    is FavResultState.Loading -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is FavResultState.Success -> {
                        binding.progressBar.visibility = View.VISIBLE
                        favArticleList = it.articleList
                        favAdapter.setData(favArticleList)
                    }
                    else -> {
                        binding.progressBar.visibility = View.GONE
                        Log.i(TAG, "getFavDataFromDatabase: $it")
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