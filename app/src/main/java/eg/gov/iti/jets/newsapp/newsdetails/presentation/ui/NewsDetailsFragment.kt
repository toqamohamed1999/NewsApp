package eg.gov.iti.jets.newsapp.newsdetails.presentation.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.databinding.FregmentNewsDetailsBinding
import eg.gov.iti.jets.newsapp.favourite.data.local.FavLocalSourceImp
import eg.gov.iti.jets.newsapp.favourite.data.repo.FavouriteRepoImp
import eg.gov.iti.jets.newsapp.newsdetails.data.repo.NewsDetailsNewsRepoImpl
import eg.gov.iti.jets.newsapp.newsdetails.presentation.viewmodel.NewsDetailsViewModel
import eg.gov.iti.jets.newsapp.newsdetails.presentation.viewmodel.NewsDetailsViewModelFactory
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.convertToFavorite
import kotlinx.coroutines.launch

class NewsDetailsFragment : Fragment() {

    private  var isFavorite:Boolean? = false
    lateinit var binding: FregmentNewsDetailsBinding
    private lateinit var  viewModel:NewsDetailsViewModel
    private val args: NewsDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FregmentNewsDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = NewsDetailsViewModelFactory(NewsDetailsNewsRepoImpl(FavouriteRepoImp.getFavRepoInstance(FavLocalSourceImp.getFavLocalSourceInstance())))
        viewModel = ViewModelProvider(this,factory)[NewsDetailsViewModel::class.java]
        Picasso.get().load(args.newsDetails.urlToImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .resize(245, view.width)
            .into(binding.imageViewNews)
        binding.textViewTitle.text = args.newsDetails.title
        binding.textViewAutherName.text = args.newsDetails.author
        binding.textViewDescription.text = args.newsDetails.description
        binding.favoriteBtn.setOnClickListener{
            if(isFavorite == false)
            {
                isFavorite = true
                binding.favoriteBtn.background = ResourcesCompat.getDrawable(resources,R.drawable.baseline_favorite_24,null)
                args.newsDetails.convertToFavorite().let { it1 -> viewModel.addNewsToFavorite(it1) }
            }else{
                isFavorite = false
                binding.favoriteBtn.background = ResourcesCompat.getDrawable(resources,R.drawable.baseline_favorite_border_24,null)
                args.newsDetails.articleId.let { it1 -> viewModel.deleteFromFavorite(it1) }
            }
        }
        lifecycleScope.launch {
            viewModel.isFavorite.collect {
                if(it)
                {
                    isFavorite = true
                    binding.favoriteBtn.background = ResourcesCompat.getDrawable(resources,R.drawable.baseline_favorite_24,null)

                }else{
                    isFavorite = false
                    binding.favoriteBtn.background = ResourcesCompat.getDrawable(resources,R.drawable.baseline_favorite_border_24,null)
                }
            }
        }
        args.newsDetails.articleId.let { viewModel.isFavorite(it) }
    }
}