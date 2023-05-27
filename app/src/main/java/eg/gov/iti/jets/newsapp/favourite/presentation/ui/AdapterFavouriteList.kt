package eg.gov.iti.jets.newsapp.favourite.presentation.ui

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import eg.gov.iti.jets.newsapp.databinding.FragmentFavouriteListBinding
import eg.gov.iti.jets.newsapp.databinding.RowFavouriteListBinding
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article

class AdapterFavouriteList (private var articalList: List<Article>) :
    RecyclerView.Adapter<AdapterFavouriteList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return ViewHolder(RowFavouriteListBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int =articalList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articalList[position])
    }

    fun setData(value: List<Article>){
        this.articalList = value

    }

    class ViewHolder(private val binding: RowFavouriteListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {

            binding.titleFavouriteTextview.text = article.title

            Picasso.get().load(article.urlToImage)
                .into(binding.imageFavourite)

            binding.deleteFavouritImage.setOnClickListener{

            }
        }
    }
}