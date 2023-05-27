package eg.gov.iti.jets.newsapp.newsscreen.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import eg.gov.iti.jets.newsapp.newsscreen.domain.model.Article

class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.content == newItem.content
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}