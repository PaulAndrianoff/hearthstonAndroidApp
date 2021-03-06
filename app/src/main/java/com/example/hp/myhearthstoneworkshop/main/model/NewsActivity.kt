package com.example.hp.myhearthstoneworkshop.main.model

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.hp.myhearthstoneworkshop.R
import com.example.hp.myhearthstoneworkshop.main.fastAdapter.NewsItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {

    private val new1 = News("titre 1", "mon subtitle", "Blaize parle beaucoup trop avec les profs.")
    private val news2 = News("titre 2", "mon super subtitle", "Octave à encore blamé Blaize.")
    private val listOfNews:Array<News> = arrayOf(new1, news2, new1, news2, new1, news2, new1, news2, new1, news2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        displayNews(listOfNews)

    }

    fun displayNews(listOfNews: Array<News>) {
        val monAdapter = FastItemAdapter<NewsItem>()
        for (contact: News in listOfNews) {
            val item = NewsItem(contact)
            monAdapter.add(item)
        }

        recyclerView.adapter = monAdapter

        val monLinearLayoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
        val gridLayoutManager = GridLayoutManager(this,
                3,
                GridLayoutManager.VERTICAL,
                false) // THIS IS AN EXAMPLE
        recyclerView.layoutManager = monLinearLayoutManager

        monAdapter.withOnClickListener({ view, adapter, item, position ->
            val intent = Intent(this, NewsArticleActivity::class.java)
            intent.putExtra("title", item.news.title)
            intent.putExtra("subtitle", item.news.subtitle)
            intent.putExtra("content", item.news.content)
            startActivity(intent)
            return@withOnClickListener true
        })
    }
}
