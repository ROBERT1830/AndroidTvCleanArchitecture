package com.robertconstantindinescu.mytvapp.feature_browser.presentation.old_movies_screen

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.palette.graphics.Palette
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.Category
import com.robertconstantindinescu.mytvapp.feature_browser.domain.model.old_shared_movie.SharedOldMovie
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.old_movies_screen.presenter.OldMoviesPosterWithImagePresenter
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.util.Constants.TV_APP_NAME
import com.robertconstantindinescu.mytvapp.util.DataSourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BrowserOldMoviesFragment: BrowseSupportFragment() {

    private val viewModel: BrowserOldMoviesViewModel by viewModels()

//    private val backGroundManager by lazy {
//        BackgroundManager.getInstance(requireActivity()).apply {
//            attach(requireActivity().window)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = TV_APP_NAME
        if (savedInstanceState == null){
            prepareEntranceTransition()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(BrowserOldMoviesEvent.SearchOldMovies(requireActivity().applicationContext))

        observeData()

        //setDynamicBackground()
    }

    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when(it){
                    is DataSourceState.Empty -> {
                        Unit
                    }
                    is DataSourceState.Loading -> {

                    }
                    is DataSourceState.Success -> {
                        displayData(it.data)
                        startEntranceTransition()
                    }
                    else -> {Unit}

                }
            }
        }

    }

    private fun displayData(categories: List<Category>) {
        val adapter = ArrayObjectAdapter(ListRowPresenter())
        for (category in categories){
            val headerItem = HeaderItem(category.id, category.type)
            val rowAdapter = ArrayObjectAdapter(OldMoviesPosterWithImagePresenter())
            for (movie in category.movie){
                rowAdapter.add(movie)
            }
            adapter.add(ListRow(headerItem, rowAdapter))
        }
        this.adapter = adapter



    }

//    private fun setDynamicBackground() {
//        setOnItemViewSelectedListener { itemViewHolder, _, rowPresenter, _ ->
//            if (itemViewHolder?.view != null) {
//
//                val bitmapDrawable =
//                    (itemViewHolder.view as ImageCardView).mainImageView.drawable as? BitmapDrawable
//
//                if (bitmapDrawable != null) {
//                    Palette.from(bitmapDrawable.bitmap).generate { palette ->
//                        (palette?.vibrantSwatch ?: palette?.dominantSwatch)?.let { swatch ->
//                            backGroundManager.color = swatch.rgb
//                        }
//                    }
//                }
//            }
//        }
//    }

}