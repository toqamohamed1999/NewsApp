package eg.gov.iti.jets.newsapp.newsscreen.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import eg.gov.iti.jets.newsapp.R
import eg.gov.iti.jets.newsapp.databinding.ArticleItemBinding
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article


class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffUtil()) {

    private lateinit var binding: ArticleItemBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ArticleItemBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)

        Picasso.get().load(article.urlToImage)
            .placeholder(R.drawable.article)
            .resize(200, 200)
            .into(holder.binding.image)
        holder.binding.titleTextview.text = article.title
        if(article.author.isNullOrEmpty()) holder.binding.autherTextview.visibility = View.GONE
        else holder.binding.autherTextview.text = article.author
        holder.binding.descriptionTextview.text = article.description
        holder.binding.publishedAtTextview.text = article.publishedAt
        holder.binding.favCardView.setOnClickListener{

            holder.binding.root.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(article)
            )
        }
    }

    inner class ArticleViewHolder(var binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}