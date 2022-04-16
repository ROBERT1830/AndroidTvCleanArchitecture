package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.palette.graphics.Palette
import com.robertconstantindinescu.mytvapp.R
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.Category
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.SharedMovie
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen.presenter.PosterWithImagePresenter
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen.presenter.PosterWithTextPresenter
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.util.Constants.TV_APP_NAME
import com.robertconstantindinescu.mytvapp.util.DataSourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BrowserScreenFragment: BrowseSupportFragment()  {

    private val viewModel: BrowserScreenViewModel by viewModels()
    //private lateinit var adapter: ArrayObjectAdapter
    private val backGroundManager by lazy {
        BackgroundManager.getInstance(requireActivity()).apply {
            attach(requireActivity().window)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = TV_APP_NAME
        if (savedInstanceState == null){
            prepareEntranceTransition()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        //setOldMovieOption()
        setOnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            // TODO: implement
        }
        setDynamicBackground()
    }

    private fun setOldMovieOption() {
        val oldMoviesHeaderItem = HeaderItem(100, "Old Movies")
        val headerAdapter  = ArrayObjectAdapter()
        headerAdapter.add(oldMoviesHeaderItem)
        this.headersSupportFragment.adapter = headerAdapter

    }

    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.onEvent(BrowserScreenEvent.SearchMovies)
            viewModel.state.collect {
                when(it){
                    is DataSourceState.Empty -> {
                        //Do nothing
                        Unit
                    }
                    is DataSourceState.Loading -> {
                        Log.d("Loading", "IS_LOADING")

                    }
                    is DataSourceState.Success -> {
                        Log.d("SUCCESS", "IS_SUCCESS")
                        displayData(it.data)
                        startEntranceTransition()
                    }

                }
            }
        }

    }

    private fun displayData(categories: List<Category>) {
        val adapter = ArrayObjectAdapter(ListRowPresenter())
        for (category in categories){
            val headerItem = HeaderItem(category.id, category.genre)
            val rowAdapter = ArrayObjectAdapter(PosterWithImagePresenter())
            for (movie in category.movies){
                rowAdapter.add(movie)
            }
            adapter.add(ListRow(headerItem, rowAdapter))
        }

        val oldMoviesHeaderItem = HeaderItem(100, "Old Movies")
        val oldMoviesAdapter = ArrayObjectAdapter(PosterWithTextPresenter())
        oldMoviesAdapter.add(resources.getString(R.string.watch_old_movies))
        adapter.add(ListRow(oldMoviesHeaderItem, oldMoviesAdapter))
        this.adapter = adapter


    }

    private fun setDynamicBackground() {
        setOnItemViewSelectedListener { itemViewHolder, _, rowPresenter, _ ->
            if (itemViewHolder?.view != null) {
                if (itemViewHolder.view is ImageCardView){
                    val bitmapDrawable =
                        (itemViewHolder.view as ImageCardView).mainImageView.drawable as? BitmapDrawable

                    if (bitmapDrawable != null) {
                        Palette.from(bitmapDrawable.bitmap).generate { palette ->
                            (palette?.vibrantSwatch ?: palette?.dominantSwatch)?.let { swatch ->
                                backGroundManager.color = swatch.rgb
                            }
                        }
                    }
                }



            }
        }
    }

}































