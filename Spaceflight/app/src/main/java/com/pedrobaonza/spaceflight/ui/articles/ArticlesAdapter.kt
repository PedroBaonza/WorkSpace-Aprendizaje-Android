package com.pedrobaonza.spaceflight.ui.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedrobaonza.spaceflight.R
import com.pedrobaonza.spaceflight.data.model.Article

class ArticlesAdapter : ListAdapter<Article, ArticlesAdapter.ArticleViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageArticle: ImageView = itemView.findViewById(R.id.imageArticle)
        private val titleArticle: TextView = itemView.findViewById(R.id.titleArticle)
        private val summaryArticle: TextView = itemView.findViewById(R.id.summaryArticle)

        fun bind(article: Article) {
            titleArticle.text = article.title
            summaryArticle.text = article.summary

            Glide.with(itemView)
                .load(article.image_url)
                .into(imageArticle)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }
}
